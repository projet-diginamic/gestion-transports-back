package dev.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.ModifierVehiculeServiceDto;
import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.dto.vehiculeService.VehiculeServiceListeDtoCollaborateur;
import dev.exception.NotFoundException;
import dev.services.VehiculeServiceService;

@RestController
@RequestMapping("vehicule-service")
public class VehiculeServiceController {

	private VehiculeServiceService vehiculeServiceService;

	public VehiculeServiceController(VehiculeServiceService vehiculeServiceService) {
		super();
		this.vehiculeServiceService = vehiculeServiceService;
	}

	@GetMapping
	public List<VehiculeServiceListeDto> listerVehiculesService(@RequestParam Integer start,
			@RequestParam Integer size) {
		return this.vehiculeServiceService.afficherVehiculesService(PageRequest.of(start, size));
	}

	@GetMapping("collaborateur")
	public List<VehiculeServiceListeDtoCollaborateur> listerVehiculesServiceCollaborateur(@RequestParam Integer start,
			@RequestParam Integer size) {
		return this.vehiculeServiceService.afficherVehiculesServiceCollaborateur(PageRequest.of(start, size));
	}

	@PostMapping
	public ResponseEntity<?> creerVehiculeService(@RequestBody CreerVehiculeServiceDto creerVehiculeServiceDto) {
		return this.vehiculeServiceService.creerVehiculeService(creerVehiculeServiceDto);
	}

	@PutMapping
	public ResponseEntity<?> modifierVehiculeService(@RequestBody ModifierVehiculeServiceDto modifierVehiculeServiceDto)
			throws NotFoundException {
		return ResponseEntity.ok(this.vehiculeServiceService.modifierVehiculeService(modifierVehiculeServiceDto));
	}

	@PutMapping("/archiver/{id}")
	public ResponseEntity<?> archiverVehiculeService(@PathVariable Integer id) throws NotFoundException {
		return ResponseEntity.ok(this.vehiculeServiceService.archiverVehiculeService(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> detailVehiculeService(@PathVariable Integer id) throws NotFoundException {
		return ResponseEntity.ok(this.vehiculeServiceService.detailVehiculeService(id));
	};

	@GetMapping("/marque/{marque}")
	public Iterable<VehiculeServiceListeDto> rechercherVehiculeParMarque(@PathVariable String marque) {
		return this.vehiculeServiceService.vehiculeParMarque(marque);
	}

	@GetMapping("/immatriculation/{immatriculation}")
	public Iterable<VehiculeServiceListeDto> rechercherVehiculeParImmatriculation(
			@PathVariable String immatriculation) {
		return this.vehiculeServiceService.vehiculeParImmatriculation(immatriculation);
	}

}
