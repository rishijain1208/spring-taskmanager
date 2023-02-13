package com.example.springtaskmanager.controllers;

import com.example.springtaskmanager.dtos.ErrorResponse;
import com.example.springtaskmanager.entities.TaskEntity;
import com.example.springtaskmanager.services.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskEntity>> getAllTasks()
    {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskEntity> createTask(@RequestBody @NotNull TaskEntity task)
    {
        var newTask = taskService.createTask(new TaskEntity(task.getTitle(),task.getDescription(),task.getCompleted(),task.getDueDate()));
        return ResponseEntity.created(URI.create("/getTaskById/"+newTask.getId())).body(newTask);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Optional<TaskEntity>> updateTask(@PathVariable("id") Integer id,@RequestBody TaskEntity task)
    {
        var updatedTask = taskService.updateTask(id,task);
        return ResponseEntity.accepted().body(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Optional<TaskEntity>> deleteTask(@PathVariable("id") Integer id)
    {
        var task = taskService.deleteTask(id);
        return ResponseEntity.accepted().body(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/tasks/title")
    public ResponseEntity<Optional<TaskEntity>> getTaskByTitle(@RequestParam(value = "title") String title)
    {
        return ResponseEntity.ok(taskService.getTasksByTitle(title));
    }

    @GetMapping("/tasks/status")
    public ResponseEntity<List<TaskEntity>> getTaskByStatus(@RequestParam(value = "status") Boolean status)
    {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }


    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TaskService.@NotNull TaskNotFoundException e)
    {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()),HttpStatus.NOT_FOUND);
    }
}
