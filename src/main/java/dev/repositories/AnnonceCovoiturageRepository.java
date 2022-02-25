package dev.repositories;

import dev.entites.AnnonceCovoiturage;
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

    List<AnnonceCovoiturage> findByStatutLike(String statut);

    @Query(value =
            "SELECT * FROM annonce_covoiturage "+
                    "LEFT JOIN adresse_depart ON annonce_covoiturage.adresse_depart = adresse_depart.id "+
                    "LEFT JOIN adresse_arrivee ON annonce_covoiturage.adresse_arrivee = adresse_arrivee.id "+
                    "WHERE "+
                    " annonce_covoiturage.statut='OUVERT' "+
                    "AND (:date is NULL or :date = date(date_heure_depart)) "+
                    "AND (:addrDep is NULL or adresse_depart.ville = :addrDep) "+
                    "AND (:addrArr is NULL or adresse_arrivee.ville = :addrArr)", nativeQuery = true)
    List<AnnonceCovoiturage> rechercher(
                                        @Param("addrDep") String a,
                                        @Param("addrArr") String b,
                                        @Param("date") LocalDate c);

    List<AnnonceCovoiturage> findByOrganisateurIdAndStatutLike(Integer id, String statut);
}
