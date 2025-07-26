<template>
  <el-dialog
    :model-value="props.visible"
    @update:modelValue="emit('update:visible', $event)"
  >
    <el-form label-width="100px">
      <el-form-item label="活动简述">
        <el-input v-model="taskForm.title" />
      </el-form-item>
      <el-form-item label="预计番茄数">
        <el-rate
          v-model="taskForm.estimatePomodoroCnt1st"
          :max="8"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="onSubmit">创建</el-button>
      </el-form-item>

    </el-form>
  </el-dialog>
</template>

<script setup>
import { reactive, ref, onMounted } from "vue";
import { ChatRound, Apple, Plus } from "@element-plus/icons-vue";
import { createTask } from "@/api/tasks.ts"
import { onSuccess, onError } from "@/utils/common.ts"
const props = defineProps({
  visible: { type: Boolean, default: false },
});
const emit = defineEmits(["update:visible", "refresh"]);

const icons = [Apple];

const taskForm = reactive({
  title: "",
  status: 1,
  estimatePomodoroCnt1st: 0,
});

const addTask = async () => {
  createTask(taskForm)
    .then((res) => {
      onSuccess("创建活动成功", res)
      emit("refresh");
    })
    .catch((error) => onError("获取管理器信息失败", error));
};

const onSubmit = () => { addTask() };
</script>

<style></style>
