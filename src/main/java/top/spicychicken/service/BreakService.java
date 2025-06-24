package top.spicychicken.service;

import java.util.List;

import top.spicychicken.entity.Break;

public interface BreakService {
    Break startBreak(Integer pomodoroId);
    Break endBreak(Integer breakId);
    List<Break> getBreaksByPomodoro(Integer pomodoroId);
}
