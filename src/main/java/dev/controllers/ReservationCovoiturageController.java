package dev.controllers;

import dev.dto.reservation.covoiturage.*;
import dev.entites.reservation.ReservationCovoiturage;
import dev.services.ReservationCovoiturageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des réservations de covoiturage
 */
@RestController
@RequestMapping("resa/covoiturage")
public class ReservationCovoiturageController {

    private ReservationCovoiturageService covoiturageService;

    public ReservationCovoiturageController(ReservationCovoiturageService covoiturageService) {
        this.covoiturageService = covoiturageService;
    }

    /**
     * Liste paginée des réservations
     * @param start
     * @param size
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ReservationCovoiturage>> listerToutesReservations(@RequestParam Integer start, @RequestParam Integer size){
        return ResponseEntity.ok(this.covoiturageService.listerToutesReservations(PageRequest.of(start,size)));
    }

    /**
     * Liste de toutes les résas covoiturage
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<ReservationCovoiturage>> lister(){
        return ResponseEntity.ok(this.covoiturageService.lister());
    }

    /**
     * Liste des réservations d'un passager donné
     * @param id_utilisateur
     * @return
     */
    @GetMapping("passager/{id_utilisateur}")
    public List<ReservationCovoiturageSimpleDto> afficherReservations(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservationsParUtilisateur(Integer.parseInt(id_utilisateur));
    }

    /**
     * Liste des réservations à venir d'un passager donné
     * @param id_utilisateur
     * @return
     */
    @GetMapping("passager-avenir/{id_utilisateur}")
    public List<ReservationCovoiturageSimpleDto> afficherReservationsAvenir(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservationsParUtilisateurAvenir(Integer.parseInt(id_utilisateur));
    }

    /**
     * Liste des réservations passées d'un passager donné
     * @param id_utilisateur
     * @return
     */
    @GetMapping("passager-histo/{id_utilisateur}")
    public List<ReservationCovoiturageSimpleDto> afficherReservationsHisto(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservationsParUtilisateurHisto(Integer.parseInt(id_utilisateur));
    }

    /**
     * Renvoie une réservation donnée par id
     * @param id_resa
     * @return
     */
    @GetMapping("{id_resa}")
    public ResponseEntity<ReservationCovoiturageDetailDto> afficherUneReservation(@PathVariable String id_resa){
            return ResponseEntity.ok(this.covoiturageService.afficherUneReservation(Integer.parseInt(id_resa)));
    }

    /**
     * Insère une réservation de covoiturage
     * @param resa
     * @return
     */
    @PostMapping
    public ResponseEntity<ReservationCovoiturage> reserverCovoiturage(@RequestBody CreerReservationCovoiturageDto resa){
            return ResponseEntity.ok(this.covoiturageService.reserverCovoiturage(resa));
    }

    /**
     * Supprime une résa par id
     * @param id_resa
     * @return
     */
    @DeleteMapping("{id_resa}")
    public ResponseEntity<String> supprimerReservationCovoiturage(@PathVariable String id_resa){
            this.covoiturageService.annulerReservationCovoiturage(Integer.parseInt(id_resa));
            return ResponseEntity.ok(id_resa);
    }

    /**
     * Ecrase une résa en base
     * @param resa
     * @return
     */
    @PutMapping
    public ResponseEntity<ReservationCovoiturage> modifierResa(@RequestBody ModifierReservationCovoiturageDto resa){
            return ResponseEntity.ok(this.covoiturageService.modifierReservationCovoiturage(resa));
    }


}
