package com.ratedpower.task.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import com.ratedpower.task.dto.TaskDTO;
import com.ratedpower.task.model.Task;
import com.ratedpower.task.service.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Method to get all tasks
     * 
     * @param filterBy not required. Filter by priority or/and completetion
     * @param orderBy  not required. Ordered by priority or creation date
     * @return list ordered
     */
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(@RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String priority, Pageable pageable) {
        return ResponseEntity.ok(
                taskService.getAllTasks(pageable, completed, priority).stream().map(this::convertToDto).collect(Collectors.toList()));
    }

    /**
     * Method to find task by id
     * 
     * @param taskId task Id for search
     * @return task or ResponseStatusException if not found
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int taskId) {
        return ResponseEntity.ok(convertToDto(taskService.getTask(taskId)));
    }

    /**
     * Method to add task to the database
     * 
     * @param taskDTO taskDTO with all fields and subtasks
     * @return task created
     */
    @PostMapping("")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(taskService.addTask(task)));
    }

    /**
     * Method to edit task by taskId and the object to edit
     * 
     * @param taskId  task id of the object to edit
     * @param taskDTO task object to save on database
     * @return the edited object
     */
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> editTask(@PathVariable int taskId, @RequestBody TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(convertToDto(taskService.editTask(taskId, task)));
    }

    /**
     * Method to edit task by taskId and the object to edit
     * 
     * @param taskId  task id of the object to edit
     * @param taskDTO task object to save on database
     * @return the edited object
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Method to convert to DTO one task
     * 
     * @param task entity to convert
     * @return dto converted
     */
    private TaskDTO convertToDto(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    /**
     * Method to convert to entity one dto
     * 
     * @param task dto to convert
     * @return entity converted
     */
    private Task convertToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
