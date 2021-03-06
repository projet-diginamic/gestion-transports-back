package dev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.entites.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

}
