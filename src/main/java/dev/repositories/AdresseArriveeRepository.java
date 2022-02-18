package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entites.AdresseArrivee;

@Repository
public interface AdresseArriveeRepository extends JpaRepository<AdresseArrivee, Integer> {

}
