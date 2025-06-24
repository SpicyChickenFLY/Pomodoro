package top.spicychicken.service;

import java.util.List;

import top.spicychicken.entity.Task;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Integer id);

    Task createTask(Task task);

    Task updateTask(Integer id, Task task);

    void deleteTask(Integer id);
}
