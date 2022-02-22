package dev.controllers;

import dev.dto.reservation.covoiturage.*;
import dev.exception.entites.reservation.ReservationCovoiturage;
import dev.services.ReservationCovoiturageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("resa/covoiturage")
public class ReservationCovoiturageController {

    private ReservationCovoiturageService covoiturageService;

    public ReservationCovoiturageController(ReservationCovoiturageService covoiturageService) {
        this.covoiturageService = covoiturageService;
    }

    @GetMapping
    public List<ReservationCovoiturage> listerToutesReservations(@RequestParam Integer start, @RequestParam Integer size){
        return this.covoiturageService.listerToutesReservations(PageRequest.of(start,size));
    }

    @GetMapping("passager/{id_utilisateur}")
    public List<ReservationCovoiturageSimpleDto> afficherReservations(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservationsParUtilisateur(Integer.parseInt(id_utilisateur));
    }

    @GetMapping("passager-avenir/{id_utilisateur}")
    public List<ReservationCovoiturageSimpleDto> afficherReservationsAvenir(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservationsParUtilisateurAvenir(Integer.parseInt(id_utilisateur));
    }

    @GetMapping("passager-histo/{id_utilisateur}")
    public List<ReservationCovoiturageSimpleDto> afficherReservationsHisto(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservationsParUtilisateurHisto(Integer.parseInt(id_utilisateur));
    }

    @GetMapping("{id_resa}")
    public ResponseEntity<ReservationCovoiturageDetailDto> afficherUneReservation(@PathVariable String id_resa){
            return ResponseEntity.ok(this.covoiturageService.afficherUneReservation(Integer.parseInt(id_resa)));
    }

    @PostMapping
    public ResponseEntity<ReservationCovoiturage> reserverCovoiturage(@RequestBody CreerReservationCovoiturageDto resa){
            return ResponseEntity.ok(this.covoiturageService.reserverCovoiturage(resa));
    }

    @DeleteMapping("{id_resa}")
    public ResponseEntity<String> supprimerReservationCovoiturage(@PathVariable String id_resa){
            this.covoiturageService.annulerReservationCovoiturage(Integer.parseInt(id_resa));
            return ResponseEntity.ok(id_resa);
    }

    @PutMapping
    public ResponseEntity<ReservationCovoiturage> modifierResa(@RequestBody ModifierReservationCovoiturageDto resa){
            return ResponseEntity.ok(this.covoiturageService.modifierReservationCovoiturage(resa));
    }


}
