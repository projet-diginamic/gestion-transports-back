package dev.repositories;

import dev.dto.ReservationCovoiturageSimpleDto;
import dev.entites.Utilisateur;
import dev.entites.reservation.ReservationCovoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface ReservationCovoiturageRepository extends JpaRepository<ReservationCovoiturage, Integer> {

    @Query("SELECT COUNT(*) FROM ReservationCovoiturage r WHERE r.annonceCovoiturage.id=:id")
    Integer calculerNbPlacesReservees(@Param("id") Integer id);

    @Query("SELECT r FROM ReservationCovoiturage r WHERE r.passager.id=:id ")
    List<ReservationCovoiturage> listerReservationsParUtilisateur(@Param("id") Integer id);

    List<ReservationCovoiturage> findByPassagerIdAndAnnonceCovoiturageDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime date);

    List<ReservationCovoiturage> findByPassagerIdAndAnnonceCovoiturageDateHeureDepartLessThan(Integer id, LocalDateTime date);
}
