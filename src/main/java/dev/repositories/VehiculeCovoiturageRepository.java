package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.exception.entites.VehiculeCovoiturage;

@Repository
public interface VehiculeCovoiturageRepository extends JpaRepository<VehiculeCovoiturage, Integer> {

}
