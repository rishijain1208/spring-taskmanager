package com.example.springtaskmanager.controllers;

import com.example.springtaskmanager.dtos.ErrorResponse;
import com.example.springtaskmanager.entities.NoteEntity;
import com.example.springtaskmanager.services.NoteService;
import com.example.springtaskmanager.services.TaskService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class NoteController {

    final NoteService noteService;

    final TaskService taskService;

    public NoteController(NoteService noteService, TaskService taskService) {
        this.noteService = noteService;
        this.taskService = taskService;
    }

    @GetMapping("/tasks/{id}/notes")
    public ResponseEntity<List<NoteEntity>> getAllNotesByTaskId(@PathVariable("id") Integer id)
    {
        return ResponseEntity.ok(noteService.getAllNotesByTaskId(id));
    }

    @PostMapping("/tasks/{id}/notes")
    public ResponseEntity<NoteEntity> createNoteByTaskId(@RequestBody @NotNull NoteEntity note, @PathVariable("id") Integer id)
    {
        var task = taskService.getTaskById(id);
        var newNote = new NoteEntity(note.getBody(),task);
        var n = noteService.createNoteByTaskId(newNote);
        return ResponseEntity.created(URI.create("/getNoteById/"+ n.getId())).body(newNote);
    }

    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TaskService.@NotNull TaskNotFoundException e)
    {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
