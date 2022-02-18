package dev.services;

import dev.entites.reservation.ReservationCovoiturage;
import dev.repositories.ReservationCovoiturageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationCovoiturageService {

    private ReservationCovoiturageRepository reservationCovoiturageRepository;


    public ReservationCovoiturageService(ReservationCovoiturageRepository reservationCovoiturageRepository) {
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
    }

    public List<ReservationCovoiturage> afficherReservations(Integer id_utilisateur) {
        return this.reservationCovoiturageRepository.findAllById(List.of(id_utilisateur));
    }

    public ReservationCovoiturage reserverCovoiturage(ReservationCovoiturage reservationCovoiturage) {
        return this.reservationCovoiturageRepository.save(reservationCovoiturage);
    }

    public void supprimerReservationCovoiturage(Integer id_resa){
        this.reservationCovoiturageRepository.deleteById(id_resa);
    }

    public ReservationCovoiturage modifierReservationCovoiturage(ReservationCovoiturage resa) throws Exception{
        this.reservationCovoiturageRepository
                .findById(resa.getId())
                .orElseThrow(Exception::new);
        this.supprimerReservationCovoiturage(resa.getId());
        return this.reserverCovoiturage(resa);
    }
}
