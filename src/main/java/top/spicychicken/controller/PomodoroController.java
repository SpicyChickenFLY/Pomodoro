package top.spicychicken.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Pomodoro;
import top.spicychicken.entity.Task;
import top.spicychicken.service.PlanService;
import top.spicychicken.service.PomodoroService;
import top.spicychicken.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PomodoroController {

    private final PomodoroService pomodoroService;
    private final TaskService taskService;
    private final PlanService planService;
    // private final InterruptionService interruptionService;
    // private final PomodoroStatsService pomodoroStatsService;

    // 任务相关接口
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping("/task/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable Integer id, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    // 番茄钟核心操作接口
    @PostMapping("/task/{id}/focus/start")
    public ResponseEntity<Pomodoro> startPomodoro(@RequestParam Integer taskId) {
        return ResponseEntity.ok(pomodoroService.startFocus(taskId));
    }

    @PostMapping("/task/{id}/focus/abort")
    public ResponseEntity<Pomodoro> abortPomodoro(@PathVariable Integer id) {
        return ResponseEntity.ok(pomodoroService.completeFocus(id));
    }

    @PostMapping("/task/{id}/focus/complete")
    public ResponseEntity<Pomodoro> completePomodoro(@PathVariable Integer id) {
        return ResponseEntity.ok(pomodoroService.completeFocus(id));
    }

    // // 中断记录接口
    // @PostMapping("/{id}/interrupt")
    // public ResponseEntity<Interruption> recordInterruption(
    //         @PathVariable Integer id,
    //         @RequestParam Integer type) {
    //     return ResponseEntity.ok(interruptionService.recordInterruption(id, type));
    // }

    // 计划接口
    @PostMapping("/plan")
    public ResponseEntity<Plan> createPlan(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate planDate,
            @RequestParam Integer status) {
        return ResponseEntity.ok(planService.createPlan(planDate, status));
    }

    @GetMapping("/plan/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Integer id) {
        return ResponseEntity.ok(planService.getPlanById(id));
    }

    @GetMapping("/plan/date")
    public ResponseEntity<List<Plan>> getPlanByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(planService.getPlanByDate(date));
    }

    @GetMapping("/plans/status")
    public ResponseEntity<List<Plan>> getPlansByStatus(@RequestParam Integer status) {
        return ResponseEntity.ok(planService.getPlansByStatus(status));
    }

    @PostMapping("/plan/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Integer id, @RequestBody Plan plan) {
        return ResponseEntity.ok(planService.updatePlan(id, plan));
    }

    @DeleteMapping("/plan/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer id) {
        planService.deletePlan(id);
        return ResponseEntity.ok("Plan deleted successfully");
    }

    // Task和Plan关联管理
    @PostMapping("/plan/{planId}/task/{taskId}")
    public ResponseEntity<String> addTaskToPlan(@PathVariable Integer planId, 
                                               @PathVariable Integer taskId, 
                                               @RequestParam Integer planType) {
        planService.addTaskToPlan(planId, taskId, planType);
        return ResponseEntity.ok("Task added to plan successfully");
    }

    @DeleteMapping("/plan/{planId}/task/{taskId}")
    public ResponseEntity<String> removeTaskFromPlan(@PathVariable Integer planId, @PathVariable Integer taskId) {
        planService.removeTaskFromPlan(planId, taskId);
        return ResponseEntity.ok("Task removed from plan successfully");
    }

    @GetMapping("/task/{taskId}/plans")
    public ResponseEntity<List<Plan>> getPlansByTask(@PathVariable Integer taskId) {
        return ResponseEntity.ok(planService.getPlansByTaskId(taskId));
    }

    // // 统计报表接口
    // @GetMapping("/report/daily")
    // public ResponseEntity<List<Pomodoro>> getDailyReport(
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    //     return ResponseEntity.ok(pomodoroService.getDailyReport(date));
    // }

    // @GetMapping("/report/task/{taskId}")
    // public ResponseEntity<Map<String, Object>> getTaskStats(@PathVariable Integer taskId) {
    //     return ResponseEntity.ok(pomodoroStatsService.getTaskStats(taskId));
    // }
    //
    // @GetMapping("/report/summary")
    // public ResponseEntity<Map<String, Object>> getSummaryReport(
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
    //     return ResponseEntity.ok(pomodoroStatsService.getSummaryReport(startDate, endDate));
    // }
}
