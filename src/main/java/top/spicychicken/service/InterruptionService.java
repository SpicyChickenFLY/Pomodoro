package top.spicychicken.service;

import java.util.List;
import java.util.Map;

import top.spicychicken.entity.Interruption;


public interface InterruptionService {
    Interruption recordInterruption(Integer pomodoroId, Integer type);
    List<Interruption> getInterruptionsByPomodoro(Integer pomodoroId);
    Map<String, Long> getInterruptionStats(Integer pomodoroId);
}
