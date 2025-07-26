package top.spicychicken.service;

import java.time.LocalDate;
import java.util.List;

import top.spicychicken.entity.Plan;

public interface PlanService {
    Plan createPlan(LocalDate planDate, Integer status);

    Plan getPlanById(Integer id);

    List<Plan> getPlanByDate(LocalDate date);

    List<Plan> getPlansByStatus(Integer status);

    Plan updatePlan(Integer id, Plan plan);

    void deletePlan(Integer id);

    // 添加Task和Plan关联的方法
    void addTaskToPlan(Integer planId, Integer taskId, Integer planType);

    void removeTaskFromPlan(Integer planId, Integer taskId);

    List<Plan> getPlansByTaskId(Integer taskId);
}
