package dev.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.dto.vehiculeService.VehiculeServiceListeDtoCollaborateur;
import dev.entites.VehiculeService;

import java.util.List;

@Repository
public interface VehiculeServiceRepository extends JpaRepository<VehiculeService, Integer> {

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto(v.id, v.immatriculation, v.marque, v.modele, v.photo, v.categorie.nom) from VehiculeService v "
			+ "inner join v.categorie c ")
	List<VehiculeServiceListeDto> listerVehicules(Pageable pageable);

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDtoCollaborateur(v.id, v.marque, v.modele, v.immatriculation, v.photo) from VehiculeService v where v.statut = 'En service'")
	List<VehiculeServiceListeDtoCollaborateur> listerVehiculesCollaborateur(Pageable pageable);

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto"
			+ " (v.id, v.immatriculation, v.marque, v.modele, v.photo, v.categorie.nom) from VehiculeService v inner join v.categorie c where v.marque like %:marque%")
	Iterable<VehiculeServiceListeDto> rechercherParMarque(@Param("marque") String marque);

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto"
			+ " (v.id, v.immatriculation, v.marque, v.modele, v.photo, v.categorie.nom) from VehiculeService v inner join v.categorie c where v.immatriculation like %:immatriculation%")
	Iterable<VehiculeServiceListeDto> rechercherParImmatriculation(@Param("immatriculation") String immatriculation);

	@Query(value = "SELECT * FROM vehicule "
			+ "LEFT JOIN reservation_vehicule ON vehicule.id = reservation_vehicule.id_vehicule " + "WHERE "
			+ "(vehicule.statut = 'En service' AND :date != date(reservation_vehicule.date_heure_depart)) ", nativeQuery = true)
	List<VehiculeService> rechercherEnServiceDateLibre(@Param("date") LocalDate date);

}
