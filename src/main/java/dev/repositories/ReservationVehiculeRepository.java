package dev.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entites.reservation.ReservationVehicule;

/**
 * Repository Réservation Véhicule
 */
public interface ReservationVehiculeRepository extends JpaRepository<ReservationVehicule, Integer> {

	List<ReservationVehicule> findByPassagerId(Integer id);

	List<ReservationVehicule> findByPassagerIdAndDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime d);

	List<ReservationVehicule> findByPassagerIdAndDateHeureDepartLessThan(Integer id, LocalDateTime d);

	List<ReservationVehicule> findByPassagerIdAndStatutLike(Integer id, String statut);

	List<ReservationVehicule> findByChauffeurIdAndStatutLike(Integer id, String statut);

	List<ReservationVehicule> findByVehiculeId(Integer id);

	List<ReservationVehicule> findByVehiculeIdAndStatutLike(Integer id, String statut);

	List<ReservationVehicule> findByVehiculeIdAndDateHeureDepartLessThan(Integer id, LocalDateTime d);

	List<ReservationVehicule> findByVehiculeIdAndDateHeureDepartGreaterThanEqual(Integer id, LocalDateTime d);

	@Query("Select r from ReservationVehicule r where r.dateHeureDepart > :date and r.demandeChauffeur = true and r.chauffeur is null")
	List<ReservationVehicule> enAttente(@Param("date") LocalDateTime now);
}
