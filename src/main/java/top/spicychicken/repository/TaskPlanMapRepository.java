package top.spicychicken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.spicychicken.entity.TaskPlanMap;
import top.spicychicken.entity.TaskPlanMap.TaskPlanMapId;

public interface TaskPlanMapRepository extends JpaRepository<TaskPlanMap, TaskPlanMapId> {
    List<TaskPlanMap> findByTaskId(Integer taskId);
    List<TaskPlanMap> findByPlanId(Integer planId);
    List<TaskPlanMap> findByPlanType(Integer planType);
}

