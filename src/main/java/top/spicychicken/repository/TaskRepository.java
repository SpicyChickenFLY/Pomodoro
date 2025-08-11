package top.spicychicken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import top.spicychicken.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(Integer status);

    List<Task> findByTitle(String title);

    // Page<Task> findAll(Example<Task> example, Pageable pageable);

    // Task restoreTask(String title);
}
