package top.spicychicken.service;

import java.time.LocalDate;
import java.util.Map;


public interface PomodoroStatsService {
    Map<String, Object> getTaskStats(Integer taskId);
    Map<String, Object> getSummaryReport(LocalDate startDate, LocalDate endDate);
    Map<LocalDate, Long> getDailyPomodoroStats(LocalDate startDate, LocalDate endDate);
}
