package com.example.springtaskmanager.repositories;

import com.example.springtaskmanager.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity,Integer> {
    List<NoteEntity> findAllByTask_Id(Integer id);
}
