package top.spicychicken.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Task;
import top.spicychicken.entity.TaskPlanMap;
import top.spicychicken.repository.PlanRepository;
import top.spicychicken.repository.TaskPlanMapRepository;
import top.spicychicken.repository.TaskRepository;
import top.spicychicken.service.PlanService;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final TaskPlanMapRepository taskPlanMapRepository;
    private final TaskRepository taskRepository;

    @Override
    public Plan createPlan(LocalDate planDate, Integer status) {
        Plan plan = new Plan();
        plan.setPlanDate(planDate);
        plan.setStatus(status);
        return planRepository.save(plan);
    }

    @Override
    public Plan getPlanById(Integer id) {
        return planRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Plan> getPlanByDate(LocalDate date) {
        return planRepository.findByPlanDate(date);
    }

    @Override
    public List<Plan> getPlansByStatus(Integer status) {
        return planRepository.findByStatus(status);
    }

    @Override
    public Plan updatePlan(Integer id, Plan delta) {
        Plan plan = planRepository.findById(id).orElseThrow(RuntimeException::new);
        if (delta.getPlanDate() != null) {
            plan.setPlanDate(delta.getPlanDate());
        }
        if (delta.getStatus() != null) {
            plan.setStatus(delta.getStatus());
        }
        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Integer id) {
        planRepository.deleteById(id);
    }

    @Override
    public void addTaskToPlan(Integer planId, Integer taskId, Integer planType) {
        Plan plan = planRepository.findById(planId).orElseThrow(RuntimeException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        
        TaskPlanMap taskPlanMap = new TaskPlanMap();
        taskPlanMap.setPlan(plan);
        taskPlanMap.setTask(task);
        taskPlanMap.setPlanType(planType);
        
        taskPlanMapRepository.save(taskPlanMap);
    }

    @Override
    public void removeTaskFromPlan(Integer planId, Integer taskId) {
        List<TaskPlanMap> taskPlanMaps = taskPlanMapRepository.findByPlanId(planId);
        taskPlanMaps.stream()
            .filter(tpm -> tpm.getTask().getId().equals(taskId))
            .forEach(taskPlanMapRepository::delete);
    }

    @Override
    public List<Plan> getPlansByTaskId(Integer taskId) {
        List<TaskPlanMap> taskPlanMaps = taskPlanMapRepository.findByTaskId(taskId);
        return taskPlanMaps.stream()
            .map(TaskPlanMap::getPlan)
            .collect(Collectors.toList());
    }
}

