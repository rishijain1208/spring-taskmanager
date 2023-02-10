package com.example.springtaskmanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name = "notes")
public class NoteEntity extends BaseEntity{

    @Column(name="body",nullable = false,length=500)
    String body;

    @ManyToOne
    TaskEntity task;
}
