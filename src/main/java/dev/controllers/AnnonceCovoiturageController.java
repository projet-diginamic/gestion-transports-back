package dev.controllers;

import dev.dto.AnnonceCovoiturageDto;
import dev.dto.reservation.covoiturage.ReqCovoit;
import dev.entites.AnnonceCovoiturage;
import dev.services.AnnonceCovoiturageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrôleur des annonces covoiturages : toutes requêtes
 */
@RestController
@RequestMapping("covoiturage")
public class AnnonceCovoiturageController {

    private AnnonceCovoiturageService covoiturageService;

    public AnnonceCovoiturageController(AnnonceCovoiturageService covoiturageService){
        super();
        this.covoiturageService = covoiturageService;
    }

    /**
     * Liste paginée des annonces
     * @param start
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity<List<AnnonceCovoiturage>> listerCovoiturages(@RequestParam Integer start, @RequestParam Integer size){
        return ResponseEntity.ok(this.covoiturageService.listerCovoiturages(PageRequest.of(start,size)));
    }

    /**
     * Liste non paginée de toutes les annonces
     * @return response ok liste annonces
     */
    @GetMapping("/all")
    public ResponseEntity<List<AnnonceCovoiturage>> lister(){
        return ResponseEntity.ok(this.covoiturageService.lister());
    }

    /**
     * Annonce par id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<AnnonceCovoiturage> recupererAnnonce(@PathVariable String id){
            return ResponseEntity.ok(this.covoiturageService.recupererCovoiturage(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur (à venir)
     * @param id
     * @return
     */
    @GetMapping("/orga-avenir/{id}")
    public ResponseEntity<List<AnnonceCovoiturage>> listerAnnoncesOrgaAvenir(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaAvenir(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur (historiques)
     * @param id
     * @return
     */
    @GetMapping("/orga-histo/{id}")
    public ResponseEntity<List<AnnonceCovoiturage>> listerAnnoncesOrgaHisto(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaHisto(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur (à venir ET historiques)
     * @param id
     * @return
     */
    @GetMapping("/orga/{id}")
    public ResponseEntity<List<AnnonceCovoiturage>> listerAnnoncesOrga(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrga(Integer.parseInt(id)));
    }

    /**
     * Insérer une annonce
     * @param annonce dto
     * @return
     */
    @PostMapping
    public ResponseEntity<AnnonceCovoiturage> publierAnnonce(@RequestBody AnnonceCovoiturageDto annonce){
        return ResponseEntity.ok(this.covoiturageService.publierAnnonce(annonce));
    }

    /**
     * Supprimer une annonce
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity supprimerAnnonce(@PathVariable String id){
        return ResponseEntity.ok(this.covoiturageService.supprimerCovoiturage(Integer.parseInt(id)));
    }

    /**
     * écraser une annonce
     * @param annonce
     * @return
     */
    @PutMapping
    public ResponseEntity modifierAnnonce(@RequestBody AnnonceCovoiturage annonce){
            this.covoiturageService.modifierAnnonce(annonce);
            return ResponseEntity.ok().build();
    }

    /**
     * Rechercher une annonce filtrée par critères (tous nullables)
     * @param  {adresseDepart, adresseArrivee, date}
     * @return Liste des annonces satisfaisant aux critères
     */
    @GetMapping("rechercher")
    public ResponseEntity<List<AnnonceCovoiturage>> rechercherCovoit(@RequestParam(required = false) String date,
                                                                     @RequestParam(required = false)  String dep,
                                                                     @RequestParam(required = false) String arr){
        return ResponseEntity.ok(this.covoiturageService.rechercher(new ReqCovoit(dep,arr,LocalDate.parse(date))));
    }

}
