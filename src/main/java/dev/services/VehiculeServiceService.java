package dev.services;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.dto.vehiculeService.CreerVehiculeServiceDto;
import dev.dto.vehiculeService.ModifierVehiculeServiceDto;
import dev.dto.vehiculeService.ReqVehiculeServiceDate;
import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.dto.vehiculeService.VehiculeServiceListeDtoCollaborateur;
import dev.entites.Categorie;
import dev.entites.VehiculeService;
import dev.exception.FormatImmatriculationException;
import dev.exception.ListeVideException;
import dev.exception.NotFoundException;
import dev.exception.NotFoundImmatriculationException;
import dev.exception.NotFoundMarqueException;
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

	/**
	 * Méthode qui renvoie une liste de véhicules de service
	 * 
	 * @param pr PageRequest
	 * @return Liste véhicules service
	 */
	public List<VehiculeServiceListeDto> afficherVehiculesService(PageRequest pr) throws ListeVideException {
		if (this.vehiculeServiceRepository.listerVehicules(pr).iterator().hasNext()) {
			return this.vehiculeServiceRepository.listerVehicules(pr);
		} else {
			throw new ListeVideException("Aucune donnée disponible");
		}

	}

	/**
	 * Méthode qui renvoie une liste de véhicules de service avec statut en service
	 * - A destination des collaborateurs
	 * 
	 * @param pr
	 * @return
	 * @throws ListeVideException
	 */
	public List<VehiculeServiceListeDtoCollaborateur> afficherVehiculesServiceCollaborateur(PageRequest pr)
			throws ListeVideException {
		if (this.vehiculeServiceRepository.listerVehiculesCollaborateur(pr).iterator().hasNext()) {
			return this.vehiculeServiceRepository.listerVehiculesCollaborateur(pr);
		} else {
			throw new ListeVideException("Actuellement, aucun véhicule n'a le statut 'En service'");
		}
	}

	/**
	 * Méthode pour rechercher un véhicule au statut "en service" et libre à la date
	 * indiquée
	 * 
	 * @param req
	 * @return Liste des annonces satisfaisant les critères
	 */
	public List<VehiculeService> rechercherEnServiceDateLibre(ReqVehiculeServiceDate req) throws ListeVideException {
		if (this.vehiculeServiceRepository.rechercherEnServiceDateLibre(req.getDate()).iterator().hasNext()) {
			return this.vehiculeServiceRepository.rechercherEnServiceDateLibre(req.getDate());
		} else {
			throw new ListeVideException("Actuellement, aucun véhicule disponible le "
					+ req.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
	}

	/**
	 * Méthode pour créer un nouveau véhicule de service
	 * 
	 * @param creerVehiculeServiceDto
	 * @return
	 * @throws NotFoundException
	 * @throws FormatImmatriculationException
	 */
	@Transactional
	public ResponseEntity<?> creerVehiculeService(CreerVehiculeServiceDto creerVehiculeServiceDto)
			throws NotFoundException, FormatImmatriculationException {
		VehiculeService vehiculeService = new VehiculeService();

		Optional<Categorie> optionalCategorie = categorieRepository.findById(creerVehiculeServiceDto.getCategorie());

		if (creerVehiculeServiceDto.getImmatriculation().matches("^[A-Z]{2}[-][0-9]{3}[-][A-Z]{2}$")) {
			vehiculeService.setImmatriculation(creerVehiculeServiceDto.getImmatriculation());
		} else {
			throw new FormatImmatriculationException("La plaque d'immatriculation renseignée n'a pas le bon format");
		}

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

	/**
	 * Méthode qui permet de modifier les informations d'un véhicule de service
	 * 
	 * @param modifierVehiculeServiceDto
	 * @return
	 * @throws NotFoundException
	 * @throws FormatImmatriculationException
	 */
	@Transactional
	public ResponseEntity<?> modifierVehiculeService(ModifierVehiculeServiceDto modifierVehiculeServiceDto)
			throws NotFoundException, FormatImmatriculationException {

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

				if (modifierVehiculeServiceDto.getImmatriculation().matches("^[A-Z]{2}[-][0-9]{3}[-][A-Z]{2}$")) {
					vehiculeService.setImmatriculation(modifierVehiculeServiceDto.getImmatriculation());
				} else {
					throw new FormatImmatriculationException(
							"La plaque d'immatriculation renseignée n'a pas le bon format");
				}

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

	/**
	 * Méthode qui permet d'archiver un véhicule (modification du statut en "Hors
	 * service"
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
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

	/**
	 * Méthode qui permet d'afficher les détails d'un véhicule de service
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public ResponseEntity<?> detailVehiculeService(Integer id) throws NotFoundException {

		Optional<VehiculeService> optionalVehiculeService = this.vehiculeServiceRepository.findById(id);

		if (optionalVehiculeService.isPresent()) {
			// véhicule trouvé
			VehiculeService vehiculeService = optionalVehiculeService.get();

			return ResponseEntity.ok(vehiculeService);

		} else {
			// véhicule non trouvé
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("véhicule non trouvé");
		}

	}

	/**
	 * Méthode qui permet de filtrer les véhicules par marque
	 * 
	 * @param marque
	 * @return
	 */
	public Iterable<VehiculeServiceListeDto> vehiculeParMarque(String marque) throws NotFoundMarqueException {
		if (this.vehiculeServiceRepository.rechercherParMarque(marque).iterator().hasNext()) {
			return this.vehiculeServiceRepository.rechercherParMarque(marque);
		} else {
			throw new NotFoundMarqueException("Aucune marque ne correspond à votre recherche");
		}

	}

	/**
	 * Méthode qui permet de filtrer les véhicules par immatriculation
	 * 
	 * @param immatriculation
	 * @return
	 * @throws NotFoundImmatriculationException
	 */
	public Iterable<VehiculeServiceListeDto> vehiculeParImmatriculation(String immatriculation)
			throws NotFoundImmatriculationException {
		if (this.vehiculeServiceRepository.rechercherParImmatriculation(immatriculation).iterator().hasNext()) {
			return this.vehiculeServiceRepository.rechercherParImmatriculation(immatriculation);
		} else {
			throw new NotFoundImmatriculationException("Aucune immatriculation ne correspond à votre recherche");
		}
	}

}
