package dev.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.entites.VehiculeService;

@Repository
public interface VehiculeServiceRepository extends JpaRepository<VehiculeService, Integer> {

	@Query("select new dev.dto.vehiculeService.VehiculeServiceListeDto(v.id, v.immatriculation, v.marque, v.modele, v.categorie, v.photo) from VehiculeService v")
	List<VehiculeServiceListeDto> listerVehicules(Pageable pageable);

}