package com.example.springtaskmanager.services;

import com.example.springtaskmanager.entities.NoteEntity;
import com.example.springtaskmanager.entities.TaskEntity;
import com.example.springtaskmanager.repositories.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TaskService {

    public static class TaskNotFoundException extends IllegalArgumentException
    {
        public TaskNotFoundException(String id)
        {
            super("Task with id " + id + " not found");
        }
    }

    final TasksRepository tasksRepository;

    final NoteService noteService;

    public TaskService(TasksRepository tasksRepository, NoteService noteService) {
        this.tasksRepository = tasksRepository;
        this.noteService = noteService;
    }

    public List<TaskEntity> getAllTasks()
    {
        return tasksRepository.findAll();
    };

    public TaskEntity createTask(TaskEntity task)
    {
        return tasksRepository.save(task);
    }

    public TaskEntity getTaskById(Integer id)
    {
        var task = tasksRepository.findById(id);
        if(!task.isPresent())
        {
            throw new TaskNotFoundException(id.toString());
        }
        return task.get();
    }

    public Optional<TaskEntity> getTasksByTitle(String title)
    {
        var task = tasksRepository.findByTitle(title);
        if(!task.isPresent())
        {
            throw new TaskNotFoundException(title);
        }
        return task;
    }

    public List<TaskEntity> getTasksByStatus(Boolean status)
    {
        return tasksRepository.findAllByCompleted(status);
    }

    public Optional<TaskEntity> updateTask(Integer id,TaskEntity newTask)
    {
        var task = tasksRepository.findById(id);
        if(!task.isPresent())
        {
            throw new TaskNotFoundException(id.toString());
        }
        if(newTask.getTitle() != null) {
            task.get().setTitle(newTask.getTitle());
        }
        if(newTask.getDescription() != null) {
            task.get().setDescription(newTask.getDescription());
        }
        if(newTask.getDueDate() != null) {
            task.get().setDueDate(newTask.getDueDate());
        }
        if(newTask.getCompleted() != null) {
            task.get().setCompleted(newTask.getCompleted());
        }

        return task;
    }

    public Optional<TaskEntity> deleteTask(Integer id)
    {
        var task = tasksRepository.findById(id);
        if(!task.isPresent())
        {
            throw new TaskNotFoundException(id.toString());
        }
        for(NoteEntity note:task.get().getNotes())
        {
          if(note != null)
          {
              noteService.deleteNoteByNoteId(note.getId());
          }
        }
        tasksRepository.deleteById(id);
        return task;
    }
}
