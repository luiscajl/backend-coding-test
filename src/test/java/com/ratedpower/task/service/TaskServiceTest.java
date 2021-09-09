package com.ratedpower.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.ratedpower.task.dto.TaskDTO;
import com.ratedpower.task.model.Task;
import com.ratedpower.task.repository.TaskRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService= new TaskServiceImpl();

    @Mock
    private TaskRepository taskRepository;

    @Test
    void getTaskOK() {
        Task taskMock = new Task();
        taskMock.setCompleted(false);
        taskMock.setId(1);
        Task taskResponse = taskService.getTask(1);
        Mockito. when(taskRepository.findById(1)).thenReturn(Optional.of(taskMock));
        assertEquals(false, taskResponse.isCompleted());
    }
}
