package dev.controllers;

import dev.entites.Collaborateur;
import dev.services.CollaborateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur toutes requêtes collaborateur
 */
@RestController
@RequestMapping("collaborateur")
public class CollaborateurController {

    private CollaborateurService service;

    public CollaborateurController(CollaborateurService service) {
        this.service = service;
    }

    /**
     * Insère un collaborateur en base
     * @param u
     * @return
     */
    @PostMapping
    public ResponseEntity<Integer> insererUtilisateur(@RequestBody Collaborateur u){
        return ResponseEntity.ok(this.service.insererUtilisateur(u));
    }

    /**
     * Renvoie les détails d'un collaborateur par id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Collaborateur> afficherUtilisateur(@PathVariable String id){
        return ResponseEntity.ok(this.service.afficherUtilisateur(Integer.parseInt(id)));
    }

    /**
     * Renvoie true si l'utilisateur est administrateur
     * @param id
     * @return boolean
     */
    @GetMapping("/is-admin/{id}")
    public ResponseEntity<Boolean> isAdmin(@PathVariable String id){
        return ResponseEntity.ok(this.service.isAdmin(Integer.parseInt(id)));
    }

    /**
     * Liste de tous les collaborateurs
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<Collaborateur>> lister(){
        return ResponseEntity.ok(this.service.lister());
    }
}
