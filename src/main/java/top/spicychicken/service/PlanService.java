package top.spicychicken.service;

import java.time.LocalDate;
import java.util.List;

import top.spicychicken.entity.Plan;

public interface PlanService {
    Plan getPlanById(Integer id);

    List<Plan> getPlansByDateRange(LocalDate startDate, LocalDate endDate);

    Plan createPlan(Plan plan);

    Plan updatePlan(Integer id, Plan plan);
}
