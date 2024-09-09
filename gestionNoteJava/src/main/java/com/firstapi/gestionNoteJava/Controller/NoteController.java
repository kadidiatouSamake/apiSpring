package com.firstapi.gestionNoteJava.Controller;

import com.firstapi.gestionNoteJava.models.Note;
import com.firstapi.gestionNoteJava.services.NoteService;
import com.firstapi.gestionNoteJava.services.serviceImpl.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/add")
    public ResponseEntity<?> createNote(@RequestBody Note note){
        Object result = noteService.creerNote(note);

        // Vérifiez si le résultat est un message d'erreur (String)
        if (result instanceof String) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        // Si tout va bien, renvoyer la note créée
        return ResponseEntity.ok(result);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Object result = noteService.modifierNote(id, note);

        // Vérifie si le résultat est un message d'erreur (String)
        if (result instanceof String) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        // Si tout va bien, retourner la note modifiée
        return ResponseEntity.ok(result);
    }


    @GetMapping("/one/{idNote}")
    public Object afficherNoteParId(@PathVariable Long idNote){
        return noteService.getOneNote(idNote);
    }



    @GetMapping("/list")
    public ResponseEntity<List<Note>> AllNotes(){
        List<Note> note=  noteService.getAllNote();
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/delete/{idNote}")
    public ResponseEntity<String> supprimerNote(@PathVariable Long idNote) {
        Object result = noteService.deleteNote(idNote);

        // Vérifie si le résultat est un message d'erreur ou de succès
        if (result.equals("Erreur : Note non trouvée")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((String) result);
        }

        // Retourne un message de succès
        return ResponseEntity.ok((String) result);
    }
}
