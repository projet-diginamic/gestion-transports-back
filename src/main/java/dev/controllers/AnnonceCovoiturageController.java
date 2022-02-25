package dev.controllers;

import dev.dto.AnnonceCovoiturageDetailDto;
import dev.dto.AnnonceCovoiturageDto;
import dev.dto.reservation.covoiturage.ReqCovoit;
import dev.entites.AnnonceCovoiturage;
import dev.exception.ListeVideException;
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
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerCovoiturages(@RequestParam Integer start, @RequestParam Integer size) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerCovoiturages(PageRequest.of(start,size)));
    }

    /**
     * Liste non paginée de toutes les annonces
     * @return response ok liste annonces
     */
    @GetMapping("/all")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> lister() throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.lister());
    }

    /**
     * Liste non paginée de toutes les annonces statut OUVERT
     * @return
     * @throws ListeVideException
     */
    @GetMapping("/all-ouvert")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerActives() throws ListeVideException{
        return ResponseEntity.ok(this.covoiturageService.listerActives());
    }

    /**
     * Liste non paginée de toutes les annonces statut ARCHIVE
     * @return
     * @throws ListeVideException
     */
    @GetMapping("/all-archive")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerArchives() throws ListeVideException{
        return ResponseEntity.ok(this.covoiturageService.listerArchives());
    }

    /**
     * Liste non paginée de toutes les annonces
     * @return
     * @throws ListeVideException
     */
    @GetMapping("/all-annule")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnule() throws ListeVideException{
        return ResponseEntity.ok(this.covoiturageService.listerAnnule());
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
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrgaAvenir(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaAvenir(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur (historiques)
     * @param id
     * @return
     */
    @GetMapping("/orga-histo/{id}")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrgaHisto(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaHisto(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur (à venir ET historiques)
     * @param id
     * @return
     */
    @GetMapping("/orga/{id}")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrga(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrga(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur statut OUVERT
     * @param id
     * @return
     */
    @GetMapping("/orga-ouvert/{id}")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrgaActif(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaActif(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur statut ARCHIVE
     * @param id
     * @return
     */
    @GetMapping("/orga-archive/{id}")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrgaArchive(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaArchive(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur statut ANNULE
     * @param id
     * @return
     */
    @GetMapping("/orga-annule/{id}")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrgaAnnule(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaAnnule(Integer.parseInt(id)));
    }

    /**
     * Annonces par organisateur statut ANNULE ET ARCHIVE
     * @param id
     * @return
     */
    @GetMapping("/orga-archive-annule/{id}")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> listerAnnoncesOrgaAA(@PathVariable String id) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.listerAnnoncesOrgaAA(Integer.parseInt(id)));
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
     * Rechercher une annonce statut OUVERT filtrée par critères (tous nullables)
     * @param  {adresseDepart, adresseArrivee, date}
     * @return Liste des annonces satisfaisant aux critères
     */
    @GetMapping("rechercher")
    public ResponseEntity<List<AnnonceCovoiturageDetailDto>> rechercherCovoit(@RequestParam(required = false) String date,
                                                                     @RequestParam(required = false) String dep,
                                                                     @RequestParam(required = false) String arr) throws ListeVideException {
        return ResponseEntity.ok(this.covoiturageService.rechercher(new ReqCovoit(dep,arr,date)));
    }

}
