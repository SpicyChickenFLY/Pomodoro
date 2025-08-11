package top.spicychicken.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Task;
import top.spicychicken.entity.TaskPlanMap;
import top.spicychicken.service.TaskPlanMapService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskPlanMapController {

    private final TaskPlanMapService taskPlanMapService;

    @GetMapping("/task/{taskId}/plans")
    public ResponseEntity<List<Plan>> getPlansByTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskPlanMapService.getPlansByTaskId(taskId));
    }

    @GetMapping("/plan/{planId}/tasks")
    public ResponseEntity<List<Task>> getTasksByPlan(@PathVariable Integer planId) {
        return ResponseEntity.ok(taskPlanMapService.getTasksByPlanId(planId));
    }

    // Task和Plan关联管理
    @PostMapping("/plan/{planId}/task/{taskId}")
    public ResponseEntity<TaskPlanMap> addTaskToPlan(@PathVariable Integer planId, 
                                               @PathVariable Integer taskId, 
                                               @RequestBody TaskPlanMap taskPlanMap) {
        return ResponseEntity.ok(taskPlanMapService.addTaskToPlan(planId, taskId, taskPlanMap));
    }

    @DeleteMapping("/plan/{planId}/task/{taskId}")
    public ResponseEntity<String> removeTaskFromPlan(@PathVariable Integer planId, @PathVariable Integer taskId) {
        taskPlanMapService.removeTaskFromPlan(planId, taskId);
        return ResponseEntity.ok("Task removed from plan successfully");
    }
}
