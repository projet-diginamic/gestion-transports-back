package dev.controllers;

import dev.dto.AnnonceCovoiturageDto;
import dev.dto.reservation.covoiturage.ReqCovoit;
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
    public ResponseEntity<List<AnnonceCovoiturage>> listerCovoiturages(@RequestParam Integer start, @RequestParam Integer size){
        return ResponseEntity.ok(this.covoiturageService.listerCovoiturages(PageRequest.of(start,size)));
    }

    @GetMapping("{id}")
    public ResponseEntity<AnnonceCovoiturage> recupererAnnonce(@PathVariable String id){
            return ResponseEntity.ok(this.covoiturageService.recupererCovoiturage(Integer.parseInt(id)));
    }

    @GetMapping("/orga-avenir/{id}")
    public ResponseEntity<List<AnnonceCovoiturage>> listerAnnoncesOrgaAvenir(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaAvenir(Integer.parseInt(id)));
    }

    @GetMapping("/orga-histo/{id}")
    public ResponseEntity<List<AnnonceCovoiturage>> listerAnnoncesOrgaHisto(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaHisto(Integer.parseInt(id)));
    }

    @GetMapping("/orga/{id}")
    public ResponseEntity<List<AnnonceCovoiturage>> listerAnnoncesOrga(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrga(Integer.parseInt(id)));
    }

    @PostMapping
    public ResponseEntity<AnnonceCovoiturage> publierAnnonce(@RequestBody AnnonceCovoiturageDto annonce){
        return ResponseEntity.ok(this.covoiturageService.publierAnnonce(annonce));
    }

    @DeleteMapping("{id}")
    public ResponseEntity supprimerAnnonce(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.supprimerCovoiturage(Integer.parseInt(id)));
    }

    @PutMapping
    public ResponseEntity modifierAnnonce(@RequestBody AnnonceCovoiturage annonce){
            this.covoiturageService.modifierAnnonce(annonce);
            return ResponseEntity.ok().build();
    }

    @GetMapping("rechercher")
    public ResponseEntity<List<AnnonceCovoiturage>> rechercherCovoit(@RequestBody ReqCovoit req){
        return ResponseEntity.ok(this.covoiturageService.rechercher(req));
    }

}
