package com.example.springtaskmanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity{

    @Column(name="body",nullable = false,length=500)
    String body;

    @JsonBackReference
    @ManyToOne
    TaskEntity task;

    public NoteEntity(String body, TaskEntity task) {
        this.body = body;
        this.task = task;
    }

    public NoteEntity() {

    }
}
