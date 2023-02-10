package com.example.springtaskmanager.repositories;

import com.example.springtaskmanager.entities.NoteEntity;
import com.example.springtaskmanager.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity,Integer> {

    List<TaskEntity> findAllByCompleted(boolean completed);
}
