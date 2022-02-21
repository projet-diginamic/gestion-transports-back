package dev.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.entites.Categorie;
import dev.entites.VehiculeService;
import dev.exception.NotFoundException;
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
	public ResponseEntity<?> creerVehiculeService(CreerVehiculeServiceDto vehiculeServiceDto) throws NotFoundException {
		VehiculeService vehiculeService = new VehiculeService();

		Optional<Categorie> optionalCategorie = categorieRepository.findById(vehiculeServiceDto.getCategorie());

		vehiculeService.setImmatriculation(vehiculeServiceDto.getImmatriculation());
		vehiculeService.setMarque(vehiculeServiceDto.getMarque());
		vehiculeService.setModele(vehiculeServiceDto.getModele());
		vehiculeService.setNbPlaces(vehiculeServiceDto.getNbPlaces());
		vehiculeService.setPhoto(vehiculeServiceDto.getPhoto());
		// règle métier : par défaut, un nouveau véhicule a le statut "en service"
		vehiculeService.setStatut("En service");

		System.out.println(vehiculeServiceDto.getCategorie());

		if (optionalCategorie.isPresent()) {
			vehiculeService.setCategorie(optionalCategorie.get());
			return ResponseEntity.ok(this.vehiculeServiceRepository.save(vehiculeService));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La catégorie " + vehiculeServiceDto.getCategorie() + " n'existe pas.");
		}

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

	// 7. Archiver un véhicule

}
