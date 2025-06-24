package top.spicychicken.controller;

import top.spicychicken.entity.*;
import top.spicychicken.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pomodoro")
@RequiredArgsConstructor
public class PomodoroController {
    
    // 补全所有必要的服务依赖
    private final PomodoroService pomodoroService;
    private final TaskService taskService;
    private final BreakService breakService;
    private final InterruptionService interruptionService;
    private final PlanService planService;
    private final PomodoroStatsService pomodoroStatsService;

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

    // 番茄钟核心操作接口
    @PostMapping("/start")
    public ResponseEntity<Pomodoro> startPomodoro(@RequestParam Integer taskId) {
        return ResponseEntity.ok(pomodoroService.startPomodoro(taskId));
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Pomodoro> completePomodoro(@PathVariable Integer id) {
        return ResponseEntity.ok(pomodoroService.completePomodoro(id));
    }

    // 休息操作接口
    @PostMapping("/{id}/break/start")
    public ResponseEntity<Break> startBreak(@PathVariable Integer id) {
        return ResponseEntity.ok(breakService.startBreak(id));
    }

    @PostMapping("/break/{breakId}/end")
    public ResponseEntity<Break> endBreak(@PathVariable Integer breakId) {
        return ResponseEntity.ok(breakService.endBreak(breakId));
    }

    // 中断记录接口
    @PostMapping("/{id}/interrupt")
    public ResponseEntity<Interruption> recordInterruption(
            @PathVariable Integer id, 
            @RequestParam Integer type) {
        return ResponseEntity.ok(interruptionService.recordInterruption(id, type));
    }

    // 计划
