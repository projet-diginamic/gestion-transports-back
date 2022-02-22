package dev.repositories;

import dev.entites.reservation.ReservationVehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository Réservation Véhicule
 */
public interface ReservationVehiculeRepository extends JpaRepository<ReservationVehicule, Integer> {

    List<ReservationVehicule> findByPassagerId(Integer id);

    List<ReservationVehicule> findByPassagerIdAndDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime d);

    List<ReservationVehicule> findByPassagerIdAndDateHeureDepartLessThan(Integer id, LocalDateTime d);

    List<ReservationVehicule> findByChauffeurId(Integer id);

    List<ReservationVehicule> findByVehiculeId(Integer id);

    @Query("Select r from ReservationVehicule r where r.dateHeureDepart > :date and r.demandeChauffeur = true and r.chauffeur is null")
    List<ReservationVehicule> enAttente(@Param("date") LocalDateTime now);
}
