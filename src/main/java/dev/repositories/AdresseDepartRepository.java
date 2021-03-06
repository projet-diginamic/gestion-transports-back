package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entites.adresse.AdresseDepart;

@Repository
public interface AdresseDepartRepository extends JpaRepository<AdresseDepart, Integer> {

}
