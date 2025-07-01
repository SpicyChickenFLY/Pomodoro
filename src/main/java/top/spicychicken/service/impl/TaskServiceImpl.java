package top.spicychicken.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import top.spicychicken.entity.Task;
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
        return taskRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Integer id, Task delta) {
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        if (delta.getTitle() != null) {
            task.setTitle(delta.getTitle());
        }
        if (delta.getStatus() != null) {
            task.setStatus(delta.getStatus());
        }
        if (delta.getEstimatePomodoroCnt1st() != null) {
            task.setEstimatePomodoroCnt1st(delta.getEstimatePomodoroCnt1st());
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
