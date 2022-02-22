package dev.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.ModifierVehiculeServiceDto;
import dev.dto.vehiculeService.VehiculeServiceListeDto;
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

	@PostMapping
	public ResponseEntity<?> creerVehiculeService(@RequestBody CreerVehiculeServiceDto creerVehiculeServiceDto) {
		return this.vehiculeServiceService.creerVehiculeService(creerVehiculeServiceDto);
	}

	@PutMapping
	public ResponseEntity<?> modifierVehiculeService(@RequestBody ModifierVehiculeServiceDto modifierVehiculeServiceDto)
			throws NotFoundException {
		return ResponseEntity.ok(this.vehiculeServiceService.modifierVehiculeService(modifierVehiculeServiceDto));
	}

}
