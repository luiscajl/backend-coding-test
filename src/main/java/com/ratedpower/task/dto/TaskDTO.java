package com.ratedpower.task.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.ratedpower.task.model.TaskPriority;

import lombok.Data;

@Data
public class TaskDTO implements Serializable {

    private int id;

    private String description;

    private boolean completed;

    private TaskPriority priority;

    private List<SubTaskDTO> subtasks;

    private Date createdDate;

}
