package dev.controllers;

import dev.dto.TauxOccupation;
import dev.services.ChauffeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur des chauffeurs : gestion du taux d'occupation
 */
@RestController
@RequestMapping("chauffeur")
public class ChauffeurController {

    private ChauffeurService service;

    public ChauffeurController(ChauffeurService service) {
        this.service = service;
    }

    /**
     * Renvoie le taux d'occupation arrondi quotidien du chauffeur de la date d1 à la date d2
     * @param id
     * @param d1
     * @param d2
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<List<TauxOccupation>> taux(@PathVariable String id, @RequestParam String d1, @RequestParam String d2){
        return ResponseEntity.ok(this.service.taux(Integer.parseInt(id), LocalDate.parse(d1), LocalDate.parse(d2)));
    }
}
