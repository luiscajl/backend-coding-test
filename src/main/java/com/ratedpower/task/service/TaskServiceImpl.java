package com.ratedpower.task.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ratedpower.task.model.Task;
import com.ratedpower.task.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task getTask(int taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found task with Id"));
    }

    /**
     * Filters can be apply on database using criteria, but i used lambda to filter
     * results.
     */
    @Override
    public List<Task> getAllTasks(Pageable pageable, Boolean completed, String priority) {
        List<Task> tasks = taskRepository.findAll(pageable.getSort());
        return applyFilters(tasks, completed, priority);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task editTask(int taskId, Task task) {
        if (taskId == task.getId() && taskRepository.existsById(taskId)) {
            return taskRepository.save(task);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found task with the id " + taskId);
        }
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    private List<Task> applyFilters(List<Task> tasks, Boolean completed, String priority) {
        if (Objects.nonNull(completed)) {
            tasks = tasks.stream().filter(task -> completed.equals(task.isCompleted())).collect(Collectors.toList());
        }
        if (StringUtils.hasText(priority)) {
            tasks = tasks.stream().filter(task -> priority.equalsIgnoreCase(task.getPriority().toString()))
                    .collect(Collectors.toList());
        }
        return tasks;
    }

}
