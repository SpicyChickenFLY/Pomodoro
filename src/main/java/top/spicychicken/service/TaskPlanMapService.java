package top.spicychicken.service;

import java.util.List;

import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Task;
import top.spicychicken.entity.TaskPlanMap;

public interface TaskPlanMapService {
    List<Task> getTasksByPlanId(Integer planId);

    List<Plan> getPlansByTaskId(Integer taskId);

    TaskPlanMap addTaskToPlan(Integer planId, Integer taskId, TaskPlanMap taskPlanMap);

    void removeTaskFromPlan(Integer planId, Integer taskId);
}
