<template>
  <div>
    <el-button type="success" :icon="Plus" @click="addTask"
      >新建待办活动</el-button
    >
    <el-card v-for="row in taskListData" :key="row.id" :body-style='{ "text-align": "left" }'>
      <el-text>{{ row.title }}</el-text>
      <el-text v-for="i in row.estimatePomodoroCnt1st" :key="i">
        <el-icon><Star /></el-icon>
      </el-text>
    </el-card>
    <Add :visible="addTaskVisible" />
  </div>
</template>

<script setup>
import {
  defineProps,
  ref,
  reactive,
  onMounted,
  onBeforeMount,
  onBeforeUnmount,
  computed,
} from "vue";
import axios from "axios";
import moment from "moment";
import { ElNotification, ElLoading, ElMessageBox } from "element-plus";
import { Star, Plus } from "@element-plus/icons-vue";
import Add from "./add.vue";
import { getTaskList } from "@/api/tasks.ts";

const timer = reactive(null);

const taskListData = ref([]);
const addTaskVisible = ref(false);
const num = ref();

onMounted(async () => {
  refresh();
  // const timer = setInterval(() => refresh(), 5000);
  // onBeforeMount(() => clearInterval(timer));
  // onBeforeUnmount(() => clearInterval(timer));
});

const onError = async (msg, error) => {
  ElNotification({
    title: "失败",
    message: `${msg} - ${error}`,
    type: "error",
  });
};

const refresh = async () => getTaskInfo();

const getTaskInfo = async () => {
  getTaskList()
    .then((res) => {
      taskListData.value = res.data;
    })
    .catch((error) => onError("获取管理器信息失败", error));
};

const addTask = () => {
  addTaskVisible.value = true
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
