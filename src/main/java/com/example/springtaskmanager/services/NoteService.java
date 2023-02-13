package com.example.springtaskmanager.services;

import com.example.springtaskmanager.entities.NoteEntity;
import com.example.springtaskmanager.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    final NotesRepository notesRepository;

    public NoteService( NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public List<NoteEntity> getAllNotesByTaskId(Integer id)
    {
        return notesRepository.findAllByTask_Id(id);
    }

    public NoteEntity createNoteByTaskId(NoteEntity note)
    {
        return notesRepository.save(note);
    }

    public void deleteNoteByNoteId(Integer id)
    {
        notesRepository.deleteById(id);
    }
}
