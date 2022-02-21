package dev.controllers;

import dev.entites.AnnonceCovoiturage;
import dev.services.AnnonceCovoiturageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("covoiturage")
public class AnnonceCovoiturageController {

    private AnnonceCovoiturageService covoiturageService;

    public AnnonceCovoiturageController(AnnonceCovoiturageService covoiturageService){
        super();
        this.covoiturageService = covoiturageService;
    }

    @GetMapping
    public List<AnnonceCovoiturage> listerCovoiturages(@RequestParam Integer start, @RequestParam Integer size){
        return this.covoiturageService.listerCovoiturages(PageRequest.of(start,size));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> recupererAnnonce(@PathVariable String id){
            return ResponseEntity.ok(this.covoiturageService.recupererCovoiturage(Integer.parseInt(id)));
    }

    @PostMapping
    public AnnonceCovoiturage publierAnnonce(@RequestBody AnnonceCovoiturage annonce){
        return this.covoiturageService.publierAnnonce(annonce);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> supprimerAnnonce(@PathVariable String id){
            this.covoiturageService.supprimerCovoiturage(Integer.parseInt(id));
            return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> modifierAnnonce(@RequestBody AnnonceCovoiturage annonce){
            this.covoiturageService.modifierAnnonce(annonce);
            return ResponseEntity.ok().build();
    }

}
