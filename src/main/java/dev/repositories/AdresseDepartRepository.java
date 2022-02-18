package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entites.AdresseDepart;

@Repository
public interface AdresseDepartRepository extends JpaRepository<AdresseDepart, Integer> {

}
