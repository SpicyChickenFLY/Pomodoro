import axios from "axios";

export interface Plan {
  id: number;
  planDate: string;
  status: number;
}

export interface PlanQueryParams {
  date?: string;
  status?: number;
}

export const getPlanList = (params?: PlanQueryParams) => {
  return axios.get<Plan[]>("/api/plans", { params });
};

export const getPlansByTask = (taskId: number) => {
  return axios.get<Plan[]>(`/api/task/${taskId}/plans`);
};

export const createPlan = (data: { planDate: string; status: number }) => {
  return axios.post<Plan>("/api/plan", data);
};

export const addTaskToPlan = (planId: number, taskId: number, planType: number) => {
  return axios.post(`/api/plan/${planId}/task/${taskId}`, { planType });
};

// 其他可能的API方法
// export const updatePlan = (id: number, data: Partial<Plan>) => {
//   return axios.put(`/api/plan/${id}`, data);
// };
// 
// export const deletePlan = (id: number) => {
//   return axios.delete(`/api/plan/${id}`);
// };

