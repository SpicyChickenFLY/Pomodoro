<template>
  <el-tabs type="border-card" v-model="activeTab">
    <el-tab-pane label="专注" name="专注">
      <pomodoro v-if="activeTab === '专注'" />
    </el-tab-pane>
    <el-tab-pane label="计划" name="计划">
      <plan v-if="activeTab === '计划'" />
    </el-tab-pane>
    <el-tab-pane label="活动" name="活动">
      <task v-if="activeTab === '活动'" />
    </el-tab-pane>
    <el-tab-pane label="统计" name="统计">
    </el-tab-pane>
  </el-tabs>
</template>

<script setup>
import { defineProps, ref, reactive, onMounted, onBeforeMount } from "vue";
import Task from "./task/index.vue";
import Pomodoro from "./pomodoro.vue";
import Plan from "./plan.vue";
const activeTab = ref("活动");

</script>

<style scoped>
:deep(.el-switch){
  --el-switch-on-color: #95d475;
  --el-switch-off-color: #f89898;
}
</style>
