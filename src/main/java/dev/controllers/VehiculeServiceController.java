package dev.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public List<VehiculeServiceListeDto> listerVehiculesService(@RequestParam Integer page,
			@RequestParam Integer size) {
		return this.vehiculeServiceService.afficherVehiculesService(PageRequest.of(page, size));
	}

}
