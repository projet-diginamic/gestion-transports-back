package dev.repositories;

import dev.dto.reservation.covoiturage.ReqCovoit;
import dev.entites.AnnonceCovoiturage;
import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AnnonceCovoiturageRepository extends JpaRepository<AnnonceCovoiturage,Integer> {

    List<AnnonceCovoiturage> findByOrganisateurId(Integer id);

    List<AnnonceCovoiturage> findByOrganisateurIdAndDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime date);

    List<AnnonceCovoiturage> findByOrganisateurIdAndDateHeureDepartLessThan(Integer id, LocalDateTime date);

    @Query(value =
            "SELECT * FROM annonce_covoiturage WHERE"+
            "(:date is NULL or :date = date(date_heure_depart))"+
            "AND (:addrDep is NULL or adresse_depart = :addrDep)"+
            "AND (:addrArr is NULL or adresse_arrivee = :addrArr)", nativeQuery = true)
    List<AnnonceCovoiturage> rechercher(
                                        @Param("addrDep") Integer a,
                                        @Param("addrArr") Integer b,
                                        @Param("date") LocalDate c);
}
