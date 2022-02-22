package dev.repositories;

import dev.entites.reservation.ReservationVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository Réservation Véhicule
 */
public interface ReservationVehiculeRepository extends JpaRepository<ReservationVehicule, Integer> {

    List<ReservationVehicule> findByPassagerId(Integer id);

    List<ReservationVehicule> findByPassagerIdAndDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime d);

    List<ReservationVehicule> findByPassagerIdAndDateHeureDepartLessThan(Integer id, LocalDateTime d);


}
