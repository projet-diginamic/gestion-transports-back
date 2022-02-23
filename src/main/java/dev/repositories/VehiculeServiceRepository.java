package dev.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.dto.vehiculeService.VehiculeServiceListeDtoCollaborateur;
import dev.entites.VehiculeService;

@Repository
public interface VehiculeServiceRepository extends JpaRepository<VehiculeService, Integer> {

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto(v.id, v.immatriculation, v.marque, v.modele, v.photo, v.categorie.nom) from VehiculeService v "
			+ "inner join v.categorie c ")
	List<VehiculeServiceListeDto> listerVehicules(Pageable pageable);

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDtoCollaborateur(v.id, v.marque, v.modele, v.immatriculation, v.photo) from VehiculeService v ")
	List<VehiculeServiceListeDtoCollaborateur> listerVehiculesCollaborateur(Pageable pageable);

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto"
			+ " (v.id, v.immatriculation, v.marque, v.modele, v.photo, v.categorie.nom) from VehiculeService v inner join v.categorie c where v.marque like %:marque%")
	Iterable<VehiculeServiceListeDto> rechercherParMarque(@Param("marque") String marque);

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto"
			+ " (v.id, v.immatriculation, v.marque, v.modele, v.photo, v.categorie.nom) from VehiculeService v inner join v.categorie c where v.immatriculation like %:immatriculation%")
	Iterable<VehiculeServiceListeDto> rechercherParImmatriculation(@Param("immatriculation") String immatriculation);

}
