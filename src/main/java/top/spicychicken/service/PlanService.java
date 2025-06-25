package top.spicychicken.service;

import java.time.LocalDate;
import java.util.List;

import top.spicychicken.entity.Plan;

public interface PlanService {
    Plan createPlan(Integer taskId, LocalDate planDate);

    List<Plan> getPlansByDate(LocalDate date);

    List<Plan> getPlansByTask(Integer taskId);

    void deletePlan(Integer taskId, LocalDate planDate);
}
