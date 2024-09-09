package com.firstapi.gestionNoteJava.repo;

import com.firstapi.gestionNoteJava.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note,Long> {
    boolean existsByMatricule(String matricule);
}
