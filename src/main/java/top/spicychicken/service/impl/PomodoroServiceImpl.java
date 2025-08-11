package top.spicychicken.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Interruption;
import top.spicychicken.entity.Plan;
import top.spicychicken.entity.Pomodoro;
import top.spicychicken.entity.Task;
import top.spicychicken.repository.InterruptionRepository;
import top.spicychicken.repository.PlanRepository;
import top.spicychicken.repository.PomodoroRepository;
import top.spicychicken.repository.TaskRepository;
import top.spicychicken.service.PomodoroService;

@Service
@RequiredArgsConstructor
public class PomodoroServiceImpl implements PomodoroService {
    private final PomodoroRepository pomodoroRepository;
    private final TaskRepository taskRepository;
    private final PlanRepository planRepository;
    private final InterruptionRepository interruptionRepository;

    public Pomodoro startFocus(Integer planId, Integer taskId) {
        // 如果任务在今天日常计划内
        // 反之则是紧急计划任务,需要先紧急添加至计划
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setTask(task);
        pomodoro.setPlan(plan);
        pomodoro.setFocusDuration();
        pomodoro.setFocusStartTime(LocalDateTime.now());
        return pomodoroRepository.save(pomodoro);
    }

    public Pomodoro abortFocus(Integer pomodoroId) {
        Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Duration duration = Duration.between(pomodoro.getFocusStartTime(), LocalDateTime.now());
        pomodoro.setFocusDuration(duration);
        return pomodoroRepository.save(pomodoro);
    }

    public Pomodoro completeFocus(Integer pomodoroId) {
        Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Duration duration = Duration.between(pomodoro.getFocusStartTime(), LocalDateTime.now());
        pomodoro.setFocusDuration(duration);
        return pomodoroRepository.save(pomodoro);
    }

    public Pomodoro startBreak(Integer pomodoroId) {
        Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        pomodoro.setBreakStartTime(LocalDateTime.now());
        return pomodoroRepository.save(pomodoro);
    }

    public Pomodoro abortBreak(Integer breakId) {
        Pomodoro breakRecord = pomodoroRepository.findById(breakId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Duration duration = Duration.between(breakRecord.getBreakStartTime(), LocalDateTime.now());
        breakRecord.setBreakDuration(duration);
        return pomodoroRepository.save(breakRecord);
    }

    public Pomodoro completeBreak(Integer breakId) {
        Pomodoro breakRecord = pomodoroRepository.findById(breakId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Duration duration = Duration.between(breakRecord.getBreakStartTime(), LocalDateTime.now());
        breakRecord.setBreakDuration(duration);
        return pomodoroRepository.save(breakRecord);
    }

    public Interruption recordInterruption(Integer pomodoroId, Integer type) {
        Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Interruption interruption = new Interruption();
        interruption.setPomodoro(pomodoro);
        interruption.setInterruptionType(type);
        interruption.setStartTime(LocalDateTime.now());
        return interruptionRepository.save(interruption);
    }

    // public List<Pomodoro> getDailyReport(LocalDate date) {
    //     LocalDateTime start = date.atStartOfDay();
    //     LocalDateTime end = date.plusDays(1).atStartOfDay();
    //     return pomodoroRepository.findByStartTimeBetween(start, end);
    // }
}
