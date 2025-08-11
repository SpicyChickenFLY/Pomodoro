import axios from "axios";

export interface Plan {
  id: number;
  planDate: string;
  status: number;
}

export interface PlanQueryParams {
  dateRange?: Date[];
  status?: number;
}

export const getPlanList = (params?: PlanQueryParams) => {
  return axios.get<Plan[]>(`/api/plans`, { params });
};

export const getPlansByTask = (taskId: number) => {
  return axios.get<Plan[]>(`/api/task/${taskId}/plans`);
};

export const getPlansByDateRange = (startDate: string, endDate: string) => {
  return axios.get<Plan[]>(`/api/plans/date-range`, { params: { startDate, endDate } });
};

export const getPlanByDate = (taskId: number) => {
  return axios.get<Plan[]>(`/api/plan/date`, {});
};

export const createPlan = (data: { planDate: string; status: number }) => {
  return axios.post<Plan>("/api/plan", null, {
    params: {
      planDate: data.planDate,
      status: data.status
    }
  });
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

