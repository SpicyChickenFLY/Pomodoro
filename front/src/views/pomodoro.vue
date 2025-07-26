<template>
  <div>
    <el-progress type="circle" :percentage="50">
      <el-countdown :value="Date.now() + 1000 * 60 * 25" />
    </el-progress>
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
import { Promotion, Camera, Plus } from "@element-plus/icons-vue";


onMounted(async () => {
  // refresh();
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

// 组件数据刷新调用接口
const refresh = async () => getMgrInfo();

const getPomodoroInfo = async () => {
  //axios
  //  .put(`/api/focus/task/${taskName}`, taskData)
  //  .then((res) => {
  //    loading.close();
  //    refresh();
  //  })
  //  .catch((error) => onError("更新任务失败", error));
};

const createTask = async () => {
};

const updateTask = async (taskName, taskData) => {
  if (!taskName) {
    onError("更新任务失败", "任务名不能为空");
    return;
  }
  const loading = ElLoading.service(loadingData);
  axios
    .put(`/api/mgr/task/${taskName}`, taskData)
    .then((res) => {
      loading.close();
      refresh();
    })
    .catch((error) => onError("更新任务失败", error));
};

const deleteTask = async (taskName) => {
  if (!taskName) {
    onError("删除任务失败", "任务名不能为空");
    return;
  }
  const loading = ElLoading.service(loadingData);
  axios
    .delete(`/api/mgr/task/${taskName}`, {})
    .then((res) => {
      loading.close();
      refresh();
    })
    .catch((error) => onError("删除任务失败", error));
};

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
