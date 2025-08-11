package top.spicychicken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.spicychicken.entity.TaskPlanMap;

public interface TaskPlanMapRepository extends JpaRepository<TaskPlanMap, Integer> {
    List<TaskPlanMap> findByTaskId(Integer taskId);
    List<TaskPlanMap> findByPlanId(Integer planId);
    List<TaskPlanMap> findByPlanType(Integer planType);
}

