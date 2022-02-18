package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entites.VehiculeService;

@Repository
public interface VehiculeServiceRepository extends JpaRepository<VehiculeService, Integer> {

}
