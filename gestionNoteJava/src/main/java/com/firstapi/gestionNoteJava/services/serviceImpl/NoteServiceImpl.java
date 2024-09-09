package com.firstapi.gestionNoteJava.services.serviceImpl;

import com.firstapi.gestionNoteJava.models.Note;
import com.firstapi.gestionNoteJava.repo.NoteRepo;
import com.firstapi.gestionNoteJava.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepo noteRepo;

    @Override
        public Object creerNote(Note note) {
        // Vérifier si le matricule existe déjà
        if (noteRepo.existsByMatricule(note.getMatricule())) {
            return " le matricule existe déjà"; // Retourne null si le matricule existe
        }
        return noteRepo.save(note);
    }

    @Override
    public Object modifierNote(Long id, Note note) {
        return noteRepo.findById(id)
                .map(n -> {
                    // Vérifier si le matricule est utilisé par une autre note
                    if (!n.getMatricule().equals(note.getMatricule()) && noteRepo.existsByMatricule(note.getMatricule())) {
                        return "Erreur : Le matricule existe déjà."; // Retourner un message d'erreur
                    }
                    // Mettre à jour les champs de la note
                    n.setMatricule(note.getMatricule());
                    n.setClasse(note.getClasse());
                    n.setMatiere(note.getMatiere());
                    n.setMoyenne(note.getMoyenne());
                    return noteRepo.save(n); // Retourner la note mise à jour
                }).orElse("Erreur : Note introuvable."); // Message si la note n'est pas trouvée
    }

    @Override
    public Object getOneNote(Long id) {
        Optional<Note> note = noteRepo.findById(id);

        if (note.isEmpty()) {

            return  "Cette note n'est pas trouvée !";
        }
        else {
            return noteRepo.findById(id).get();
        }
    }

    @Override
    public List<Note> getAllNote() {
        return noteRepo.findAll();
    }

    @Override
    public Object deleteNote(Long id) {
        if (noteRepo.existsById(id)) {
            noteRepo.deleteById(id);
            return "Note supprimée avec succès";
        } else {
            return "Erreur : Note non trouvée";
        }
    }
}
