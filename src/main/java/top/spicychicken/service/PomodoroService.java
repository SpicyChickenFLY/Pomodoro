package top.spicychicken.service;

import java.time.LocalDate;
import java.util.List;

import top.spicychicken.entity.Interruption;
import top.spicychicken.entity.Pomodoro;

public interface PomodoroService {
    Pomodoro startFocus(Integer planId, Integer taskId);
    Pomodoro abortFocus(Integer pomodoroId);
    Pomodoro completeFocus(Integer pomodoroId);
    Pomodoro startBreak(Integer pomodoroId);
    Pomodoro abortBreak(Integer pomodoroId);
    Pomodoro completeBreak(Integer pomodoroId);
    Interruption recordInterruption(Integer pomodoroId, Integer type);
    // List<Pomodoro> getDailyReport(LocalDate date);
}
