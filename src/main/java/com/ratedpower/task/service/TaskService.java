package com.ratedpower.task.service;

import java.util.List;

import com.ratedpower.task.model.Task;

import org.springframework.data.domain.Pageable;

public interface TaskService {

    Task getTask(int taskId);

    List<Task> getAllTasks(Pageable pageable, Boolean completed, String priority);

    Task addTask(Task task);

    Task editTask(int taskId, Task task);

    void deleteTask(int taskId);

}
