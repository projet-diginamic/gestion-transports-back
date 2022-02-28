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

import dev.dto.reservation.covoiturage.CreerReservationCovoiturageDto;
import dev.dto.reservation.covoiturage.ModifierReservationCovoiturageDto;
import dev.dto.reservation.covoiturage.ReservationCovoiturageDetailDto;
import dev.dto.reservation.covoiturage.ReservationCovoiturageSimpleDto;
import dev.entites.reservation.ReservationCovoiturage;
import dev.exception.ListeVideException;
import dev.services.ReservationCovoiturageService;

/**
 * Contrôleur des réservations de covoiturage
 */
@RestController
@CrossOrigin
@RequestMapping("resa/covoiturage")
public class ReservationCovoiturageController {

	private ReservationCovoiturageService covoiturageService;

	public ReservationCovoiturageController(ReservationCovoiturageService covoiturageService) {
		this.covoiturageService = covoiturageService;
	}

	/**
	 * Liste paginée des réservations
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<ReservationCovoiturage>> listerToutesReservations(@RequestParam Integer start,
			@RequestParam Integer size) throws ListeVideException {
		return ResponseEntity.ok(this.covoiturageService.listerToutesReservations(PageRequest.of(start, size)));
	}

	/**
	 * Liste de toutes les résas covoiturage
	 * 
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<List<ReservationCovoiturage>> lister() throws ListeVideException {
		return ResponseEntity.ok(this.covoiturageService.lister());
	}

	/**
	 * Liste des réservations d'un passager donné
	 * 
	 * @param id_utilisateur
	 * @return
	 */
	@GetMapping("passager/{id_utilisateur}")
	public List<ReservationCovoiturageSimpleDto> afficherReservations(@PathVariable String id_utilisateur)
			throws ListeVideException {
		return this.covoiturageService.afficherReservationsParUtilisateur(Integer.parseInt(id_utilisateur));
	}

	/**
	 * Liste des réservations à venir d'un passager donné
	 * 
	 * @param id_utilisateur
	 * @return
	 */
	@GetMapping("passager-avenir/{id_utilisateur}")
	public List<ReservationCovoiturageSimpleDto> afficherReservationsAvenir(@PathVariable String id_utilisateur)
			throws ListeVideException {
		return this.covoiturageService.afficherReservationsParUtilisateurAvenir(Integer.parseInt(id_utilisateur));
	}

	/**
	 * Liste des réservations passées d'un passager donné
	 * 
	 * @param id_utilisateur
	 * @return
	 */
	@GetMapping("passager-histo/{id_utilisateur}")
	public List<ReservationCovoiturageSimpleDto> afficherReservationsHisto(@PathVariable String id_utilisateur)
			throws ListeVideException {
		return this.covoiturageService.afficherReservationsParUtilisateurHisto(Integer.parseInt(id_utilisateur));
	}

	/**
	 * Renvoie une réservation donnée par id
	 * 
	 * @param id_resa
	 * @return
	 */
	@GetMapping("{id_resa}")
	public ResponseEntity<ReservationCovoiturageDetailDto> afficherUneReservation(@PathVariable String id_resa) {
		return ResponseEntity.ok(this.covoiturageService.afficherUneReservation(Integer.parseInt(id_resa)));
	}

	/**
	 * Insère une réservation de covoiturage
	 * 
	 * @param resa
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ReservationCovoiturage> reserverCovoiturage(
			@RequestBody CreerReservationCovoiturageDto resa) {
		return ResponseEntity.ok(this.covoiturageService.reserverCovoiturage(resa));
	}

	/**
	 * Supprime une résa par id
	 * 
	 * @param id_resa
	 * @return
	 */
	@DeleteMapping("{id_resa}")
	public ResponseEntity<String> supprimerReservationCovoiturage(@PathVariable String id_resa) {
		this.covoiturageService.annulerReservationCovoiturage(Integer.parseInt(id_resa));
		return ResponseEntity.ok(id_resa);
	}

	/**
	 * Ecrase une résa en base
	 * 
	 * @param resa
	 * @return
	 */
	@PutMapping
	public ResponseEntity<ReservationCovoiturage> modifierResa(@RequestBody ModifierReservationCovoiturageDto resa) {
		return ResponseEntity.ok(this.covoiturageService.modifierReservationCovoiturage(resa));
	}

	/**
	 * Renvoie la liste des resa covoiturage ACTIVEs d'un passager
	 * 
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	@GetMapping("/passager-active/{id}")
	public ResponseEntity<List<ReservationCovoiturageSimpleDto>> listerPassagerActive(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.covoiturageService.listerParUtilisateurActive(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des resa covoiturage ARCHIVE d'un passager
	 * 
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	@GetMapping("/passager-archive/{id}")
	public ResponseEntity<List<ReservationCovoiturageSimpleDto>> listerPassagerAarchive(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.covoiturageService.listerParUtilisateurArchive(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des resa covoiturage ANNULEES d'un passager
	 * 
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	@GetMapping("/passager-annule/{id}")
	public ResponseEntity<List<ReservationCovoiturageSimpleDto>> listerPassagerAnnule(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.covoiturageService.listerParUtilisateurAnnule(Integer.parseInt(id)));
	}

	/**
	 * Renvoie la liste des resa covoit ANNULE ET ARCHIVE d'un passager
	 * 
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	@GetMapping("/passager-archive-annule/{id}")
	public ResponseEntity<List<ReservationCovoiturage>> listResponsePassagerAA(@PathVariable String id)
			throws ListeVideException {
		return ResponseEntity.ok(this.covoiturageService.listerParUtilisateurAA(Integer.parseInt(id)));
	}

}
