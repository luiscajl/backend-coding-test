package com.ratedpower.task.dto;

import java.io.Serializable;

import com.ratedpower.task.model.TaskPriority;

import lombok.Data;

@Data
public class SubTaskDTO implements Serializable {
    
    private int id;

    private String description;

    private boolean completed;

    private TaskPriority priority;
}
