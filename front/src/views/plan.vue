<template>
  <div>
    <el-date-picker
      v-model="pickDate"
      type="date"
      placeholder="选择日期"
      @panel-change="refreshPlans()"
    >
      <template #default="cell">
        <div class="celll" :class="{current: cell.isCurrent}">
          <span class="text">{{ cell.text }}</span>
          <span v-if="isPlanned(cell)" class="planned" />
        </div>
      </template>
    </el-date-picker>

    <div>
      <el-empty v-if="!isPlanned(dayjs(pickDate))" description="当日没有计划">
        <el-button v-if="dayjs(pickDate).isSame(dayjs(), 'day')" type="success" :icon="Plus" @click="createPlanOfToday">制定今日计划</el-button>
      </el-empty>
      <div v-else>
        <!-- 计划内活动 -->
        <el-divider>计划内活动</el-divider>
        <el-table :data="taskListData.filter(task => task.plans?.length > 0)">
          <el-table-column label="任务" width="180">
            <template #default="{ row }">
              <el-text>{{ row.title }}</el-text>
            </template>
          </el-table-column>
          <el-table-column label="预估番茄数" width="120">
            <template #default="{ row }">
              <el-text v-for="i in row.estimatePomodoroCnt1st" :key="i">
                <el-icon><Star /></el-icon>
              </el-text>
            </template>
          </el-table-column>
          <el-table-column label="计划状态" width="120">
            <template #default="{ row }">
              <el-tag :type="row.plans[0].status === 1 ? 'success' : 'warning'">
                {{ row.plans[0].status === 1 ? '已计划' : '计划中' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="120">
            <template #default="{ row }">
              <el-button @click="startFocus(row.id)">开始专注</el-button>
            </template>
          </el-table-column>
        </el-table>
        
        <!-- 计划外紧急 -->
        <el-divider>计划外紧急</el-divider>
        <el-table :data="taskListData.filter(task => !task.plans || task.plans.length === 0)">
          <el-table-column label="任务" width="180">
            <template #default="{ row }">
              <el-text>{{ row.title }}</el-text>
            </template>
          </el-table-column>
          <el-table-column label="预估番茄数" width="120">
            <template #default="{ row }">
              <el-text v-for="i in row.estimatePomodoroCnt1st" :key="i">
                <el-icon><Star /></el-icon>
              </el-text>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="120">
            <template #default="{ row }">
              <el-button @click="startFocus(row.id)">开始专注</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {
  ref,
  onMounted,
} from "vue";
import { ElNotification } from "element-plus";
import { Star, Plus } from "@element-plus/icons-vue";
import dayjs from 'dayjs';
import { createPlan, getPlansByDateRange } from "../api/plans.ts";
import type { Plan } from "../api/plans.ts";
import type { Task } from "../api/tasks.ts";

const taskListData = ref<Task[]>([]);
const planListData = ref<Plan[]>([]);
const planDateList = ref([]);
//const pickDate = ref(new Date().toISOString().split('T')[0]);
const pickDate = ref(new Date());

onMounted(async () => {
  await refreshPlans();
});

const onError = async (msg, error) => {
  ElNotification({
    title: "失败",
    message: `${msg} - ${error}`,
    type: "error",
  });
};

// const refreshTasks = async () => {
//   getTaskList()
//     .then((res) => {
//       // 为每个计划获取关联的任务
//       planListData.value.forEach(async (plan) => {
//         const tasksRes = await getTasksByPlan(plan.id);
//         plan.tasks = tasksRes.data;
//       });
// 
//     })
//     .catch((error) => onError("获取任务列表失败", error));
// };

const refreshPlans = async () => {
  // getPlanList({ date: pickDate.value })
  //   .then((res) => {
  //     planListData.value = res.data;
  //   })
  //   .catch((error) => onError("获取计划列表失败", error));
  const date = dayjs(pickDate.value);
  await getPlansByDateRange(
    date.clone().startOf('month').format("YYYY-MM-DD"),
    date.clone().endOf('month').format("YYYY-MM-DD")
  ).then((res) => {
    planListData.value = res.data as Plan[];
  })
};

const createPlanOfToday = async () => {
  try {
    await createPlan({
      planDate: dayjs().format("YYYY-MM-DD"),
      status: 0 // 0表示计划制定中
    });
    await refreshPlans();
    ElNotification({
      title: "成功",
      message: "今日计划已创建",
      type: "success",
    });
  } catch (error) {
    onError("创建计划失败", error);
  }
};

// const addToPlan = async (taskId) => {
//   try {
//     // 获取今天的计划
//     const planOfToday = null;
//     const plan = await getPlanList({ date: pickDate.value });
//     if (plan.data === null) {
//       // 如果没有今天的计划，先创建一个
//       plan = await createPlanOfToday();
//     }
//       // 将任务添加到新创建的计划中
//       await addTaskToPlan(plan.data.id, taskId, 1); // 1表示普通计划
//     
//     await refreshPlans();
//     ElNotification({
//       title: "成功",
//       message: "任务已添加到计划",
//       type: "success",
//     });
//   } catch (error) {
//     onError("添加任务到计划失败", error);
//   }
// };

const addTaskToPlan = async (planId, taskId, planType) => {
  // 这里需要调用API将任务添加到计划中
  // 实际实现需要根据后端API调整
  console.log(`Adding task ${taskId} to plan ${planId} with type ${planType}`);
};

const startFocus = (taskId) => {
  // 现在需要传入taskId以关联具体的任务
  console.log("开始专注任务:", taskId);
};

const isPlanned = (date: dayjs.Dayjs) => {
  return planListData.value.filter((plan) => dayjs(plan.planDate).isSame(date, 'day'));
}

const getWeekRange = (oneDayInWeek: Date)  => {
  const date = dayjs(oneDayInWeek);
  return [
    date.startOf('week').format("YYYY-MM-DD"),
    date.endOf('week').format("YYYY-MM-DD")
  ];
}

</script>

<style scoped>
:deep(.el-table--small .el-table__cell) {
  padding: 0;
}
:deep(.el-collapse-item__content) {
  padding-bottom: 5px;
}
:deep(.el-collapse-item__header) {
  height: 30px;
}
:deep(.el-table__empty-block) {
  min-height: 30px;
}
:deep(.el-table__empty-text) {
  line-height: 20px;
  height: 20px;
}
:deep(.el-switch) {
  --el-switch-on-color: #95d475;
  --el-switch-off-color: #f89898;
}
</style>
