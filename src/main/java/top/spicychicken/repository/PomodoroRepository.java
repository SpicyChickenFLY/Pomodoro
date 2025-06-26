package top.spicychicken.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import top.spicychicken.entity.Pomodoro;

public interface PomodoroRepository extends JpaRepository<Pomodoro, Integer> {
    List<Pomodoro> findByTaskId(Integer taskId);

    List<Pomodoro> findByFocusStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
