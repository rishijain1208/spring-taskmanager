package com.example.springtaskmanager.entities;

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

    @Column(name="description",nullable = true,length=500)
    String description;

    @Column(name="completed",nullable = false,columnDefinition = "boolean default false")
    Boolean completed;

    @Column(name="due_Date",nullable = true)
    Date dueDate;

    @OneToMany(mappedBy = "task")
    List<NoteEntity> notes;
}
