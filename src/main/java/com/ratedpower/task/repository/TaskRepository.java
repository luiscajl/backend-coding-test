package com.ratedpower.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;
import com.ratedpower.task.model.Task;

@Repository
@EnableJpaAuditing
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
