package dev.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.VehiculeServiceListeDto;
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
	public ResponseEntity<?> creerVehiculeService(@RequestBody CreerVehiculeServiceDto vehiculeServiceDto) {
		return this.vehiculeServiceService.creerVehiculeService(vehiculeServiceDto);
	}

//	@PutMapping
//	public ResponseEntity<?> modifierVehiculeService(@RequestBody VehiculeService vehiculeService)
//			throws NotFoundException {
//		return ResponseEntity.ok(this.vehiculeServiceService.modifierVehiculeService(vehiculeService));
//	}
	//

}
