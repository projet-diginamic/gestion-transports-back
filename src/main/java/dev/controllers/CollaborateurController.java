package dev.controllers;

import dev.entites.Collaborateur;
import dev.services.CollaborateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collaborateur")
public class CollaborateurController {

    private CollaborateurService service;

    public CollaborateurController(CollaborateurService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Integer> insererUtilisateur(@RequestBody Collaborateur u){
        return ResponseEntity.ok(this.service.insererUtilisateur(u));
    }

    @GetMapping("{id}")
    public ResponseEntity<Collaborateur> afficherUtilisateur(@PathVariable String id){
        return ResponseEntity.ok(this.service.afficherUtilisateur(Integer.parseInt(id)));
    }
}
