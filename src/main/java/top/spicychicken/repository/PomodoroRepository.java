package top.spicychicken.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import top.spicychicken.entity.Pomodoro;

public interface PomodoroRepository extends JpaRepository<Pomodoro, Integer> {
    List<Pomodoro> findByTaskId(Integer taskId);

    List<Pomodoro> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT p FROM Pomodoro p WHERE p.task.id = :taskId AND p.startTime >= :startDate")
    List<Pomodoro> findRecentByTask(@Param("taskId") Integer taskId,
            @Param("startDate") LocalDateTime startDate);
}
