package dev.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.entites.VehiculeService;
import dev.exception.NotFoundException;
import dev.repositories.VehiculeServiceRepository;

@Service
public class VehiculeServiceService {

	private VehiculeServiceRepository vehiculeServiceRepository;

	public VehiculeServiceService(VehiculeServiceRepository vehiculeServiceRepository) {
		super();
		this.vehiculeServiceRepository = vehiculeServiceRepository;
	}

	public List<VehiculeServiceListeDto> afficherVehiculesService(PageRequest pr) {
		return this.vehiculeServiceRepository.listerVehicules(pr);
	}

	@Transactional
	public VehiculeService creerVehiculeService(VehiculeService vehiculeService) {
		vehiculeService.setStatut("En service");
		return this.vehiculeServiceRepository.save(vehiculeService);
	}

	@Transactional
	public ResponseEntity<?> modifierVehiculeService(VehiculeService vehiculeService) throws NotFoundException {
		Optional<VehiculeService> optionalVehiculeService = this.vehiculeServiceRepository
				.findById(vehiculeService.getId());

		if (optionalVehiculeService.isPresent()) {
			// véhicule trouvé
			this.vehiculeServiceRepository.save(vehiculeService);
			return ResponseEntity.ok(optionalVehiculeService.get());
		} else {
			// véhicule non trouvé
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Le véhicule immatriculé " + vehiculeService.getImmatriculation() + " n'existe pas.");
		}

	}

	// 4. Liste catégorie véhicule
	// 5. Détails du véhicule (id, marque, modele, nbPlaces, photo, statut)

	// 6. Filtrer les véhicules par immatriculation
	// 6. Filtrer les véhicules par marque

	// 7. Archiver un véhicule

}
