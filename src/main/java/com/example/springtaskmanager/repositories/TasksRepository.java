package com.example.springtaskmanager.repositories;

import com.example.springtaskmanager.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity,Integer> {

    Optional<TaskEntity> findByTitle(String title);

    List<TaskEntity> findAllByCompleted(Boolean status);
}
