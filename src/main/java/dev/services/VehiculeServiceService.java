package dev.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.ModifierVehiculeServiceDto;
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
	public ResponseEntity<?> creerVehiculeService(CreerVehiculeServiceDto creerVehiculeServiceDto)
			throws NotFoundException {
		VehiculeService vehiculeService = new VehiculeService();

		Optional<Categorie> optionalCategorie = categorieRepository.findById(creerVehiculeServiceDto.getCategorie());

		vehiculeService.setImmatriculation(creerVehiculeServiceDto.getImmatriculation());
		vehiculeService.setMarque(creerVehiculeServiceDto.getMarque());
		vehiculeService.setModele(creerVehiculeServiceDto.getModele());
		vehiculeService.setNbPlaces(creerVehiculeServiceDto.getNbPlaces());
		vehiculeService.setPhoto(creerVehiculeServiceDto.getPhoto());
		// règle métier : par défaut, un nouveau véhicule a le statut "en service"
		vehiculeService.setStatut("En service");

		if (optionalCategorie.isPresent()) {
			vehiculeService.setCategorie(optionalCategorie.get());
			return ResponseEntity.ok(this.vehiculeServiceRepository.save(vehiculeService));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La catégorie " + creerVehiculeServiceDto.getCategorie() + " n'existe pas.");
		}

	}

	@Transactional
	public ResponseEntity<?> modifierVehiculeService(ModifierVehiculeServiceDto modifierVehiculeServiceDto)
			throws NotFoundException {

		Optional<VehiculeService> optionalVehiculeService = this.vehiculeServiceRepository
				.findById(modifierVehiculeServiceDto.getId());
		VehiculeService vehiculeService = optionalVehiculeService.get();

		if (modifierVehiculeServiceDto.getCategorie() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("La catégorie " + modifierVehiculeServiceDto.getCategorie() + " n'existe pas.");
		}
		Optional<Categorie> optionalCategorie = categorieRepository.findById(modifierVehiculeServiceDto.getCategorie());

		if (optionalVehiculeService.isPresent()) {

			// véhicule trouvé

			if (optionalCategorie.isPresent()) {

				vehiculeService.setCategorie(optionalCategorie.get());
				vehiculeService.setImmatriculation(modifierVehiculeServiceDto.getImmatriculation());
				vehiculeService.setMarque(modifierVehiculeServiceDto.getMarque());
				vehiculeService.setModele(modifierVehiculeServiceDto.getModele());
				vehiculeService.setNbPlaces(modifierVehiculeServiceDto.getNbPlaces());
				vehiculeService.setPhoto(modifierVehiculeServiceDto.getPhoto());
				vehiculeService.setStatut(modifierVehiculeServiceDto.getStatut());

				this.vehiculeServiceRepository.save(vehiculeService);
				return ResponseEntity.ok(vehiculeService);

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("La catégorie " + modifierVehiculeServiceDto.getCategorie() + " n'existe pas.");
			}

		} else {
			// véhicule non trouvé
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Le véhicule immatriculé " + vehiculeService.getImmatriculation() + " n'existe pas.");
		}

	}

	@Transactional
	public ResponseEntity<?> archiverVehiculeService(Integer id) throws NotFoundException {

		Optional<VehiculeService> optionalVehiculeService = this.vehiculeServiceRepository.findById(id);

		if (optionalVehiculeService.isPresent()) {
			// véhicule trouvé
			VehiculeService vehiculeService = optionalVehiculeService.get();
			vehiculeService.setStatut("hors-service");

			this.vehiculeServiceRepository.save(vehiculeService);
			return ResponseEntity.ok("véhicule " + vehiculeService.getImmatriculation() + " archivé");

		} else {
			// véhicule non trouvé
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("véhicule non trouvé");
		}

	}
	// 4. Liste catégorie véhicule

	// 5. Détails du véhicule (id, marque, modele, nbPlaces, photo, statut)

	// 6. Filtrer les véhicules par immatriculation
	// 6. Filtrer les véhicules par marque

}
