package top.spicychicken.service.impl;

import java.util.ArrayList;
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
import top.spicychicken.service.TaskPlanMapService;

@Service
@RequiredArgsConstructor
public class TaskPlanMapServiceImpl implements TaskPlanMapService {
    private final PlanRepository planRepository;
    private final TaskPlanMapRepository taskPlanMapRepository;
    private final TaskRepository taskRepository;

    public TaskPlanMap addTaskToPlan(Integer planId, Integer taskId, TaskPlanMap taskPlanMap) {
        Plan plan = planRepository.findById(planId).orElseThrow(RuntimeException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);

        taskPlanMap.setPlan(plan);
        taskPlanMap.setTask(task);
        taskPlanMap.setId(null);

        // 确保关联关系的双向绑定
        if (plan.getTaskPlanMaps() == null) {
            plan.setTaskPlanMaps(new ArrayList<>());
        }
        plan.getTaskPlanMaps().add(taskPlanMap);

        if (task.getTaskPlanMaps() == null) {
            task.setTaskPlanMaps(new ArrayList<>());
        }
        task.getTaskPlanMaps().add(taskPlanMap);

        return taskPlanMapRepository.save(taskPlanMap);
    }

    public void removeTaskFromPlan(Integer planId, Integer taskId) {
        List<TaskPlanMap> taskPlanMaps = taskPlanMapRepository.findByPlanId(planId);
        taskPlanMaps.stream()
                .filter(tpm -> tpm.getTask().getId().equals(taskId))
                .forEach(taskPlanMapRepository::delete);
    }

    public List<Task> getTasksByPlanId(Integer planId) {
        List<TaskPlanMap> taskPlanMaps = taskPlanMapRepository.findByPlanId(planId);
        return taskPlanMaps.stream()
                .map(TaskPlanMap::getTask)
                .collect(Collectors.toList());
    }

    public List<Plan> getPlansByTaskId(Integer taskId) {
        List<TaskPlanMap> taskPlanMaps = taskPlanMapRepository.findByTaskId(taskId);
        return taskPlanMaps.stream()
                .map(TaskPlanMap::getPlan)
                .collect(Collectors.toList());
    }
}
