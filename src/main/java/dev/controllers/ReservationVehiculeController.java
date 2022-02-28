package dev.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.reservation.vehicule.AccepterMissionDto;
import dev.dto.reservation.vehicule.ReservationVehiculeDto;
import dev.entites.reservation.ReservationVehicule;
import dev.exception.ListeVideException;
import dev.services.ReservationVehiculeService;

/**
 * Contrôleur des réservations de véhicules de service Toutes requêtes
 */
@RestController
@CrossOrigin
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
	public ResponseEntity<List<ReservationVehicule>> listerReservationsVehicules(@RequestParam Integer start,
			@RequestParam Integer size) throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehicules(PageRequest.of(start, size)));
	}

	/**
	 * Liste non paginée de toutes les résa véhicule
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<List<ReservationVehicule>> lister() throws ListeVideException {
		return ResponseEntity.ok(this.service.lister());
	}

	/**
	 * Renvoie une résa véhicule correspondant à l'id passé
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<ReservationVehicule> afficherResa(@PathVariable String id) {
		return ResponseEntity.ok(this.service.afficherResa(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des résas véhicule d'un utilisateur
	 */
	@GetMapping("/utilisateur/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResas(@PathVariable String id) throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResas(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des résas à venir d'un utilisateur
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("utilisateur-avenir/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResasAVenir(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResasAVenir(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des résas passées d'un utilisateur
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("utilisateur-histo/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResasHisto(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResasHisto(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des résas ACTIF d'un utilisateur
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("utilisateur-active/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResasActive(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResasActives(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des résas ARCHIVE d'un utilisateur
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("utilisateur-archive/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResasArchive(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResasArchives(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des résas ANNULE d'un utilisateur
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("utilisateur-annule/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResasAnnule(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResasAnnulees(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des resas ARCHIVE ET ANNULE d'un utilisateur
	 * 
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	@GetMapping("utilisateur-archive-annule/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerMesResasAA(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerMesResasAA(Integer.parseInt(id)));
	}

	/**
	 * Supprimer une réservation
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<ReservationVehicule> annuler(@PathVariable String id) {
		return ResponseEntity.ok(this.service.annuler(Integer.parseInt(id)));
	}

	/**
	 * Insère une nouvelle résa à partir d'un dto
	 * 
	 * @param dto
	 * @return la nouvelle résa insérée
	 */
	@PostMapping
	public ResponseEntity<ReservationVehicule> reserver(@RequestBody ReservationVehiculeDto dto) {
		return ResponseEntity.ok(this.service.reserver(dto));
	}

	/**
	 * Ecrase une résa en base
	 * 
	 * @param resa
	 * @return resa
	 */
	@PutMapping
	public ResponseEntity<ReservationVehicule> modifier(@RequestBody ReservationVehicule resa) {
		return ResponseEntity.ok(this.service.modifier(resa));
	}

	/**
	 * Renvoie toutes les résas concernant un chauffeur
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/chauffeur/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerChauffeur(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerChauffeur(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas archivées concernant un chauffeur
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/chauffeur-archive/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerChauffeurHisto(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerChauffeurArchive(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas annulées concernant un chauffeur
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/chauffeur-annule/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerChauffeurAnnule(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerChauffeurAnnule(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas concernant un véhicule de service
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/par-vehicule/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerVehicule(@PathVariable String id) throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehicule(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas concernant un véhicule de service avant la
	 * date/heure du jour
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/par-vehicule-histo/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerVehiculeHisto(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehiculeHisto(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas concernant un véhicule de service après la
	 * date/heure du jour
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/par-vehicule-avenir/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerVehiculeAvenir(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehiculeAvenir(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas ACTIF concernant un véhicule de service après la
	 * date/heure du jour
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/par-vehicule-active/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerVehiculeActif(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehiculeActive(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas ARCHIVEES concernant un véhicule de service après la
	 * date/heure du jour
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/par-vehicule-archive/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerVehiculeArchive(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehiculeArchive(Integer.parseInt(id)));
	}

	/**
	 * Renvoie toutes les résas ANNULEE concernant un véhicule de service après la
	 * date/heure du jour
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/par-vehicule-annule/{id}")
	public ResponseEntity<List<ReservationVehicule>> listerVehiculeAnnule(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.service.listerVehiculeAnnule(Integer.parseInt(id)));
	}

	/**
	 * Attribue une mission à un chauffeur
	 * 
	 * @param dto
	 * @return la résa correspondante
	 */
	@PostMapping("/accepter-mission")
	public ResponseEntity<ReservationVehicule> accepter(@RequestBody AccepterMissionDto dto) {
		return ResponseEntity.ok(this.service.accepter(dto));
	}

	/**
	 * Renvoie la liste des réservations avec chauffeur dont le chauffeur n'a pas
	 * encore été attribué
	 * 
	 * @return
	 */
	@GetMapping("en-attente-chauffeur")
	public ResponseEntity<List<ReservationVehicule>> enAttente() throws ListeVideException {
		return ResponseEntity.ok(this.service.enAttente());
	}

}
