package dev.controllers;

import dev.entites.reservation.ReservationCovoiturage;
import dev.services.ReservationCovoiturageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation/covoiturage")
public class ReservationCovoiturageController {

    private ReservationCovoiturageService covoiturageService;

    public ReservationCovoiturageController(ReservationCovoiturageService covoiturageService) {
        this.covoiturageService = covoiturageService;
    }

    @GetMapping("{id_utilisateur}")
    public List<ReservationCovoiturage> afficherReservations(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservations(Integer.parseInt(id_utilisateur));
    }

    @PostMapping("reserver")
    public ResponseEntity<?> reserverCovoiturage(@RequestBody ReservationCovoiturage reservationCovoiturage){
        try{
            return ResponseEntity.ok(this.covoiturageService.reserverCovoiturage(reservationCovoiturage));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id_resa}")
    public ResponseEntity<?> supprimerReservationCovoiturage(@RequestParam String id_resa){
        try{
            this.covoiturageService.supprimerReservationCovoiturage(Integer.parseInt(id_resa));
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("modifier")
    public ResponseEntity<?> modifierResa(@RequestBody ReservationCovoiturage resa){
        try{
            return ResponseEntity.ok(this.covoiturageService.modifierReservationCovoiturage(resa));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
