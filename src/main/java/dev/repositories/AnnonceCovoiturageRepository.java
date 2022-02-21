package dev.repositories;

import dev.entites.AnnonceCovoiturage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AnnonceCovoiturageRepository extends JpaRepository<AnnonceCovoiturage,Integer> {

    List<AnnonceCovoiturage> findByOrganisateurId(Integer id);

    List<AnnonceCovoiturage> findByOrganisateurIdAndDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime date);

    List<AnnonceCovoiturage> findByOrganisateurIdAndDateHeureDepartLessThan(Integer id, LocalDateTime date);
}
