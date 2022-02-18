package dev.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.repositories.VehiculeServiceRepository;

@Service
public class VehiculeServiceService {

	private VehiculeServiceRepository vehiculeServiceRepository;

	public VehiculeServiceService(VehiculeServiceRepository vehiculeServiceRepository) {
		super();
		this.vehiculeServiceRepository = vehiculeServiceRepository;
	}

	// *** POUR LA PARTIE ADMINISTRATEUR
	// *** VOIR LA PARTIE COLLABORATEUR POUR LES AUTRES REQUETES

	// 1. Lister les véhicules (id, immatriculation, marque, modele, photo,
	// categorie)
	public List<VehiculeServiceListeDto> afficherVehiculesService(PageRequest pr) {
		return this.vehiculeServiceRepository.listerVehicules(pr);
	}

	// 2. Ajout d'un véhicule (toutes les infos, par défaut : statut -> EN SERVICE)
	// 3. Liste catégorie véhicule
	// 4. Détails du véhicule (id, marque, modele, nbPlaces, photo, statut)
	// 5. Modification d'un véhicule (immatriculation, marque, modele, categorie,
	// nbPlaces, statut, photo)

	// 6. Filtrer les véhicules par immatriculation
	// 6. Filtrer les véhicules par marque

	// Archiver un véhicule

}
