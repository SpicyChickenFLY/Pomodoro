package top.spicychicken.service;

import org.springframework.data.domain.Page;

import top.spicychicken.common.dto.commonDto;
import top.spicychicken.entity.Task;

public interface TaskService {
    Page<Task> getAllTasks(commonDto<Task> taskDto);

    Task getTaskById(Integer id);

    Task createTask(Task task);

    Task updateTask(Integer id, Task task);
}
