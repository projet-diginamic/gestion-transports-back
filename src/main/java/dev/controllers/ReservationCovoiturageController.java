package dev.controllers;

import dev.dto.CreerReservationCovoiturageDto;
import dev.dto.ModifierReservationCovoiturageDto;
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
    public List<ReservationCovoiturage> afficherReservations(@PathVariable String id_utilisateur){
        return this.covoiturageService.afficherReservations(Integer.parseInt(id_utilisateur));
    }

    @GetMapping("{id_resa}")
    public ResponseEntity<?> afficherUneReservation(@PathVariable String id_resa){
        try{
            return ResponseEntity.ok(this.covoiturageService.afficherUneReservation(Integer.parseInt(id_resa)));
            } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> reserverCovoiturage(@RequestBody CreerReservationCovoiturageDto resa){
        try{
            return ResponseEntity.ok(this.covoiturageService.reserverCovoiturage(resa));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id_resa}")
    public ResponseEntity<?> supprimerReservationCovoiturage(@PathVariable String id_resa){
        try{
            this.covoiturageService.supprimerReservationCovoiturage(Integer.parseInt(id_resa));
            return ResponseEntity.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> modifierResa(@RequestBody ModifierReservationCovoiturageDto resa){
        try{
            return ResponseEntity.ok(this.covoiturageService.modifierReservationCovoiturage(resa));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


}
