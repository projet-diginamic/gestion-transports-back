package dev.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.TauxOccupation;
import dev.services.ChauffeurService;

/**
 * Contrôleur des chauffeurs : gestion du taux d'occupation
 */
@RestController
@CrossOrigin
@RequestMapping("chauffeur")
public class ChauffeurController {

	private ChauffeurService service;

	public ChauffeurController(ChauffeurService service) {
		this.service = service;
	}

	/**
	 * Renvoie le taux d'occupation arrondi quotidien du chauffeur de la date d1 à
	 * la date d2
	 * 
	 * @param id
	 * @param d1
	 * @param d2
	 * @return
	 */
	@GetMapping("{id}")
	public ResponseEntity<List<TauxOccupation>> taux(@PathVariable String id, @RequestParam String d1,
			@RequestParam String d2) {
		return ResponseEntity.ok(this.service.taux(Integer.parseInt(id), LocalDate.parse(d1), LocalDate.parse(d2)));
	}
}
