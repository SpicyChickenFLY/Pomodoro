package top.spicychicken.service;

import java.time.LocalDate;
import java.util.List;

import top.spicychicken.entity.Interruption;
import top.spicychicken.entity.Pomodoro;

public interface PomodoroService {
    Pomodoro startPomodoro(Integer taskId);
    Pomodoro completePomodoro(Integer pomodoroId);
    Pomodoro startBreak(Integer pomodoroId);
    Pomodoro endBreak(Integer breakId);
    Interruption recordInterruption(Integer pomodoroId, Integer type);
    List<Pomodoro> getDailyReport(LocalDate date);
}
