package top.spicychicken.service.impl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import top.spicychicken.common.dto.commonDto;
import top.spicychicken.entity.Task;
import top.spicychicken.repository.TaskRepository;
import top.spicychicken.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public Page<Task> getAllTasks(commonDto<Task> taskDto) {
        return taskRepository.findAll(taskDto.toExample(), taskDto.toPageRequest());
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Task createTask(Task task) {
        List<Task> tasks =  taskRepository.findByTitle(task.getTitle());
        if (tasks.size() > 0) {
            throw new DuplicateKeyException("活动标题存在重复");
        }
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
}
