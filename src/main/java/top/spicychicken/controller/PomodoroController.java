package top.spicychicken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Pomodoro;
import top.spicychicken.service.PomodoroService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PomodoroController {

    private final PomodoroService pomodoroService;

    // 番茄钟核心操作接口
    @PostMapping("/pomodoro")
    public ResponseEntity<Pomodoro> startPomodoro(@RequestParam Integer planId, @RequestParam Integer taskId) {
        return ResponseEntity.ok(pomodoroService.startFocus(planId, taskId));
    }

    @PostMapping("/pomodoro/{pomodoroId}/abort")
    public ResponseEntity<Pomodoro> abortPomodoro(@RequestParam Integer taskId) {
        return ResponseEntity.ok(pomodoroService.completeFocus(taskId));
    }

    @PostMapping("/pomodoro/{pomodoroId}/complete")
    public ResponseEntity<Pomodoro> completePomodoro(@RequestParam Integer planId, @RequestParam Integer taskId) {
        return ResponseEntity.ok(pomodoroService.completeFocus(taskId));
    }
}
