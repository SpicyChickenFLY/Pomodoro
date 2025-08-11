import axios from "axios";
import { Plan } from "./plans.ts";

export interface Task {
  id: number;
  status: number;
  plans?: Plan[];
}

export const getTaskList = () => {
  return axios.get("/api/tasks");
};

export const createTask = (task: any[]) => {
  return axios.post("/api/task", task);
};

export const startFocus = (task: any) => {
  return axios.post("/api/pomodoro/");
};
