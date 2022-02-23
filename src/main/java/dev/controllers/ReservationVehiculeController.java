package dev.controllers;

import dev.dto.reservation.vehicule.AccepterMissionDto;
import dev.dto.reservation.vehicule.ReservationVehiculeDto;
import dev.entites.reservation.ReservationVehicule;
import dev.services.ReservationVehiculeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur des réservations de véhicules de service
 * Toutes requêtes
 */
@RestController
@RequestMapping("resa/vehicule")
public class ReservationVehiculeController {

    private ReservationVehiculeService service;

    public ReservationVehiculeController(ReservationVehiculeService service) {
        this.service = service;
    }

    /**
     * Renvoie la liste paginée de toutes les réservations
     */
    @GetMapping
    public ResponseEntity<List<ReservationVehicule>> listerReservationsVehicules(@RequestParam Integer start, @RequestParam Integer size){
        return ResponseEntity.ok(this.service.listerVehicules(PageRequest.of(start,size)));
    }

    /**
     * Liste non paginée de toutes les résa véhicule
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<List<ReservationVehicule>> lister(){
        return ResponseEntity.ok(this.service.lister());
    }

    /**
     * Renvoie une résa véhicule correspondant à l'id passé
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<ReservationVehicule> afficherResa(@PathVariable String id){
        return ResponseEntity.ok(this.service.afficherResa(Integer.parseInt(id)));
    }

    /**
     * Renvoie la liste des résas véhicule d'un utilisateur
     */
    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<ReservationVehicule>> listerMesResas(@PathVariable String id){
        return ResponseEntity.ok(this.service.listerMesResas(Integer.parseInt(id)));
    }

    /**
     * Renvoie la liste des résas à venir d'un utilisateur
     * @param id
     * @return
     */
    @GetMapping("utilisateur-avenir/{id}")
    public ResponseEntity<List<ReservationVehicule>> listerMesResasAVenir(@PathVariable String id){
        return ResponseEntity.ok(this.service.listerMesResasAVenir(Integer.parseInt(id)));
    }

    /**
     * Renvoie la liste des résas passées d'un utilisateur
     * @param id
     * @return
     */
    @GetMapping("utilisateur-histo/{id}")
    public ResponseEntity<List<ReservationVehicule>> listerMesResasHisto(@PathVariable String id){
        return ResponseEntity.ok(this.service.listerMesResasHisto(Integer.parseInt(id)));
    }

    /**
     * Supprimer une réservation
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<ReservationVehicule> annuler(@PathVariable String id){
        return ResponseEntity.ok(this.service.annuler(Integer.parseInt(id)));
    }

    /**
     * Insère une nouvelle résa à partir d'un dto
     * @param dto
     * @return la nouvelle résa insérée
     */
    @PostMapping
    public ResponseEntity<ReservationVehicule> reserver(@RequestBody ReservationVehiculeDto dto){
        return ResponseEntity.ok(this.service.reserver(dto));
    }

    /**
     * Ecrase une résa en base
     * @param resa
     * @return resa
     */
    @PutMapping
    public ResponseEntity<ReservationVehicule> modifier(@RequestBody ReservationVehicule resa){
        return ResponseEntity.ok(this.service.modifier(resa));
    }

    /**
     * Renvoie toutes les résas concernant un chauffeur
     * @param id
     * @return
     */
    @GetMapping("/chauffeur/{id}")
    public ResponseEntity<List<ReservationVehicule>> listerChauffeur(@PathVariable String id){
        return ResponseEntity.ok(this.service.listerChauffeur(Integer.parseInt(id)));
    }

    /**
     * Renvoie toutes les résas concernant un véhicule de service
     * @param id
     * @return
     */
    @GetMapping("/vehicule/{id}")
    public ResponseEntity<List<ReservationVehicule>> listerVehicule(@PathVariable String id) {
        return ResponseEntity.ok(this.service.listerVehicule(Integer.parseInt(id)));
    }

    /**
     * Attribue une mission à un chauffeur
     * @param dto
     * @return la résa correspondante
     */
    @PostMapping("/accepter-mission")
    public ResponseEntity<ReservationVehicule> accepter(@RequestBody AccepterMissionDto dto){
        return ResponseEntity.ok(this.service.accepter(dto));
    }

    /**
     * Renvoie la liste des réservations avec chauffeur dont le chauffeur n'a pas encore été attribué
     * @return
     */
    @GetMapping("en-attente-chauffeur")
    public ResponseEntity<List<ReservationVehicule>> enAttente(){
        return ResponseEntity.ok(this.service.enAttente());
    }

}
