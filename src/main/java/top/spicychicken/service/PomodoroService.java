package top.spicychicken.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Break;
import top.spicychicken.entity.Interruption;
import top.spicychicken.entity.Pomodoro;
import top.spicychicken.entity.Task;
import top.spicychicken.repository.BreakRepository;
import top.spicychicken.repository.InterruptionRepository;
import top.spicychicken.repository.PomodoroRepository;
import top.spicychicken.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class PomodoroService {
    private final PomodoroRepository pomodoroRepository;
    private final TaskRepository taskRepository;
    private final BreakRepository breakRepository;
    private final InterruptionRepository interruptionRepository;

    public Pomodoro startPomodoro(Integer taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Pomodoro pomodoro = new Pomodoro();
        pomodoro.setTask(task);
        pomodoro.setStartTime(LocalDateTime.now());
        return pomodoroRepository.save(pomodoro);
    }

    public Pomodoro completePomodoro(Integer pomodoroId) {
        Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Duration duration = Duration.between(pomodoro.getStartTime(), LocalDateTime.now());
        pomodoro.setDuration(duration);
        return pomodoroRepository.save(pomodoro);
    }

    public Break startBreak(Integer pomodoroId) {
        Pomodoro pomodoro = pomodoroRepository.findById(pomodoroId)
                .orElseThrow(() -> new RuntimeException("Pomodoro not found"));

        Break breakRecord = new Break();
        breakRecord.setPomodoro(pomodoro);
        breakRecord.setStartTime(LocalDateTime.now());
        return breakRepository.save(breakRecord);
    }

    public Break endBreak(Integer breakId) {
        Break breakRecord = breakRepository.findById(breakId)
                .orElseThrow(() -> new RuntimeException("Break not found"));

        Duration duration = Duration.between(breakRecord.getStartTime(), LocalDateTime.now());
        breakRecord.setDuration(duration);
        return breakRepository.save(breakRecord);
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

    public List<Pomodoro> getDailyReport(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        return pomodoroRepository.findByStartTimeBetween(start, end);
    }
}
