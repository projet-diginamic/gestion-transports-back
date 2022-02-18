package dev.repositories;

import dev.entites.reservation.ReservationCovoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCovoiturageRepository extends JpaRepository<ReservationCovoiturage, Integer> {
}
