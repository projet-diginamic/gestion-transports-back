package dev.controllers;

import dev.dto.reservation.covoiturage.CreerReservationCovoiturageDto;
import dev.dto.reservation.covoiturage.ModifierReservationCovoiturageDto;
import dev.dto.reservation.covoiturage.ReservationCovoiturageSimpleDto;
import dev.entites.reservation.ReservationCovoiturage;
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
    public ResponseEntity<?> afficherUneReservation(@PathVariable String id_resa){
            return ResponseEntity.ok(this.covoiturageService.afficherUneReservation(Integer.parseInt(id_resa)));
    }

    @PostMapping
    public ResponseEntity<?> reserverCovoiturage(@RequestBody CreerReservationCovoiturageDto resa){
            return ResponseEntity.ok(this.covoiturageService.reserverCovoiturage(resa));
    }

    @DeleteMapping("{id_resa}")
    public ResponseEntity<?> supprimerReservationCovoiturage(@PathVariable String id_resa){
            this.covoiturageService.supprimerReservationCovoiturage(Integer.parseInt(id_resa));
            return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> modifierResa(@RequestBody ModifierReservationCovoiturageDto resa){
            return ResponseEntity.ok(this.covoiturageService.modifierReservationCovoiturage(resa));
    }


}
