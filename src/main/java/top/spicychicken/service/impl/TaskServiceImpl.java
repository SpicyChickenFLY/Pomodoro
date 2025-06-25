package top.spicychicken.service.impl;

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
import top.spicychicken.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Integer id, Task delta) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException());
        if (delta.getTitle() != null) {
            task.setTitle(delta.getTitle());
        }
        if (delta.getStatus() != null) {
            task.setStatus(delta.getStatus());
        }
        if (delta.getEstimatePomodoroCount() != null) {
            task.setEstimatePomodoroCount(delta.getEstimatePomodoroCount());
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
