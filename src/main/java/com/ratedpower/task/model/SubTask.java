package com.ratedpower.task.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "subtask")
@Data
public class SubTask {

    @Id
    private int id;

    private String description;

    private boolean completed;

    private TaskPriority priority;
}
