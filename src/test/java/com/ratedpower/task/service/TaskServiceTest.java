package com.ratedpower.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.ratedpower.task.model.Task;
import com.ratedpower.task.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    void getTaskOK() {
        taskService = new TaskServiceImpl(taskRepository);
        Task taskMock = new Task();
        taskMock.setCompleted(false);
        taskMock.setId(1);
        when(taskRepository.findById(1)).thenReturn(Optional.of(taskMock));
        Task taskResponse = taskService.getTask(1);
        assertEquals(false, taskResponse.isCompleted());
    }
}
