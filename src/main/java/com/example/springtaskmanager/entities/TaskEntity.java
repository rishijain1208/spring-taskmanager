package com.example.springtaskmanager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Table(indexes = @Index(columnList = "title"))
@Getter
@Setter
public class TaskEntity extends BaseEntity{

    @Column(name="title",nullable = false,length=150)
    String title;

    @Column(name="description",nullable = false,length=500)
    String description;

    @Column(name="completed",nullable = false,columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name="due_Date")
    Date dueDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "task")
    List<NoteEntity> notes;

    public TaskEntity(String title,String description, Boolean completed,Date dueDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    public TaskEntity() {

    }
}
