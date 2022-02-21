package dev.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.entites.Categorie;
import dev.entites.VehiculeService;
import dev.repositories.CategorieRepository;
import dev.repositories.VehiculeServiceRepository;

@Service
public class VehiculeServiceService {

	private VehiculeServiceRepository vehiculeServiceRepository;
	private CategorieRepository categorieRepository;

	public VehiculeServiceService(VehiculeServiceRepository vehiculeServiceRepository,
			CategorieRepository categorieRepository) {
		super();
		this.vehiculeServiceRepository = vehiculeServiceRepository;
		this.categorieRepository = categorieRepository;
	}

	public List<VehiculeServiceListeDto> afficherVehiculesService(PageRequest pr) {
		return this.vehiculeServiceRepository.listerVehicules(pr);
	}

	@Transactional
	public VehiculeService creerVehiculeService(CreerVehiculeServiceDto vehiculeServiceDto) {
		VehiculeService vehiculeService = new VehiculeService();

		Optional<Categorie> optionalCategorie = categorieRepository.findById(vehiculeServiceDto.getCategorie());

		if (optionalCategorie.isPresent()) {
			vehiculeService.setCategorie(optionalCategorie.get());
		} else {

		}

		vehiculeService.setStatut("En service");

		return this.vehiculeServiceRepository.save(vehiculeService);
	}

//	@Transactional
//	public ResponseEntity<?> modifierVehiculeService(VehiculeService vehiculeService) throws NotFoundException {
//		Optional<VehiculeService> optionalVehiculeService = this.vehiculeServiceRepository
//				.findById(vehiculeService.getId());
//
//		if (optionalVehiculeService.isPresent()) {
//			// véhicule trouvé
//			this.vehiculeServiceRepository.save(vehiculeService);
//			return ResponseEntity.ok(optionalVehiculeService.get());
//		} else {
//			// véhicule non trouvé
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body("Le véhicule immatriculé " + vehiculeService.getImmatriculation() + " n'existe pas.");
//		}
//
//	}

	// 4. Liste catégorie véhicule
	// 5. Détails du véhicule (id, marque, modele, nbPlaces, photo, statut)

	// 6. Filtrer les véhicules par immatriculation
	// 6. Filtrer les véhicules par marque

	// 7. Archiver un véhicule PUT ou PATCH ? paramètre en entrée ?

}
