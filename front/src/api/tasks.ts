import axios from "axios";

export const getTaskList = () => {
  return axios.get("/api/tasks");
};

export const createTask = (task: any[]) => {
  return axios.post("/api/task", task);
};

export const startFocus = (task: any) => {
  return axios.post("/api/pomodoro/");
};
