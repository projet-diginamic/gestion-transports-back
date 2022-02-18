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
        try{
            return ResponseEntity.ok(this.covoiturageService.recupererCovoiturage(Integer.parseInt(id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("publier")
    public AnnonceCovoiturage publierAnnonce(@RequestBody AnnonceCovoiturage annonce){
        return this.covoiturageService.publierAnnonce(annonce);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> supprimerAnnonce(@PathVariable String id){
        try{
            this.covoiturageService.supprimerCovoiturage(Integer.parseInt(id));
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("modifier")
    public ResponseEntity<?> modifierAnnonce(@RequestBody AnnonceCovoiturage annonce){
        try{
            return ResponseEntity.ok(this.covoiturageService.modifierAnnonce(annonce));
        } catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
