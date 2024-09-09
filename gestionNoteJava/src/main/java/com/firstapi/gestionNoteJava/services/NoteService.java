package com.firstapi.gestionNoteJava.services;

import com.firstapi.gestionNoteJava.models.Note;

import java.util.List;

public interface NoteService {

    Object creerNote(Note node);
    Object modifierNote(Long id, Note note);
    Object getOneNote(Long id);
    List<Note> getAllNote();
    Object deleteNote(Long id);
}
