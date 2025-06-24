package top.spicychicken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import top.spicychicken.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(Integer status);

    List<Task> findByEstimatePomodoroCountGreaterThan(Integer count);
}
