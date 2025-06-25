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

public interface PomodoroService {
    Pomodoro startPomodoro(Integer taskId);
    Pomodoro completePomodoro(Integer pomodoroId);
    Break startBreak(Integer pomodoroId);
    Break endBreak(Integer breakId);
    Interruption recordInterruption(Integer pomodoroId, Integer type);
    List<Pomodoro> getDailyReport(LocalDate date);
}
