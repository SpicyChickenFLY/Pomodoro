package top.spicychicken.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Interruption;
import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Pomodoro;
import top.spicychicken.entity.Task;
import top.spicychicken.service.InterruptionService;
import top.spicychicken.service.PlanService;
import top.spicychicken.service.PomodoroService;
import top.spicychicken.service.PomodoroStatsService;
import top.spicychicken.service.TaskService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PomodoroController {

    private final PomodoroService pomodoroService;
    private final TaskService taskService;
    // private final InterruptionService interruptionService;
    // private final PlanService planService;
    // private final PomodoroStatsService pomodoroStatsService;

    // 任务相关接口
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping("/tasks/{id}")
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

    // // 计划接口
    // @PostMapping("/plan")
    // public ResponseEntity<Plan> createPlan(
    //         @RequestParam Integer taskId,
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate planDate) {
    //     return ResponseEntity.ok(planService.createPlan(taskId, planDate));
    // }
    //
    // @GetMapping("/plan/daily")
    // public ResponseEntity<List<Plan>> getDailyPlans(
    //         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    //     return ResponseEntity.ok(planService.getPlansByDate(date));
    // }

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
