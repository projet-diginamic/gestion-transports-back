package dev.services;

import dev.dto.CreerReservationCovoiturageDto;
import dev.dto.ModifierReservationCovoiturageDto;
import dev.entites.AnnonceCovoiturage;
import dev.entites.Utilisateur;
import dev.entites.reservation.ReservationCovoiturage;
import dev.exception.CovoiturageCompletException;
import dev.exception.NotFoundException;
import dev.repositories.AnnonceCovoiturageRepository;
import dev.repositories.ReservationCovoiturageRepository;
import dev.repositories.UtilisateurRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationCovoiturageService {

    private ReservationCovoiturageRepository reservationCovoiturageRepository;
    private AnnonceCovoiturageRepository annonceCovoiturageRepository;
    private UtilisateurRepository utilisateurRepository;

    public ReservationCovoiturageService(ReservationCovoiturageRepository reservationCovoiturageRepository, AnnonceCovoiturageRepository annonceCovoiturageRepository, UtilisateurRepository utilisateurRepository) {
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    private void verifierNbPlaces(AnnonceCovoiturage annonce) throws CovoiturageCompletException {
        if(annonce.getNbPlaces() <= this.reservationCovoiturageRepository.calculerNbPlacesReservees(annonce.getId())){
            throw new CovoiturageCompletException("Plus de places disponibles sur ce covoiturage");
        }
    }

    public List<ReservationCovoiturage> afficherReservationsParUtilisateur(Integer id_utilisateur) {
        return this.reservationCovoiturageRepository.listerReservationsParUtilisateur(id_utilisateur);
    }

    public ReservationCovoiturage reserverCovoiturage(CreerReservationCovoiturageDto resa) throws NotFoundException {
        AnnonceCovoiturage annonce = this.annonceCovoiturageRepository
                .findById(resa.getIdCovoiturage())
                .orElseThrow(NotFoundException::new);
        Utilisateur collab = this.utilisateurRepository
                .findById(resa.getIdCollaborateur())
                .orElseThrow(NotFoundException::new);

        this.verifierNbPlaces(annonce);

        ReservationCovoiturage nouvelleResa = new ReservationCovoiturage();
        nouvelleResa.setAnnonceCovoiturage(annonce);
        nouvelleResa.setPassager(collab);

        return this.reservationCovoiturageRepository.save(nouvelleResa);
    }

    public void supprimerReservationCovoiturage(Integer id_resa){
        this.reservationCovoiturageRepository.deleteById(id_resa);
    }

    public ReservationCovoiturage modifierReservationCovoiturage(ModifierReservationCovoiturageDto nouvelleResa) throws NotFoundException{
        ReservationCovoiturage resa = this.reservationCovoiturageRepository
                .findById(nouvelleResa.getIdResa())
                .orElseThrow(NotFoundException::new);

        Utilisateur pax = this.utilisateurRepository
                        .findById(nouvelleResa.getIdPassager())
                                .orElseThrow(NotFoundException::new);

        AnnonceCovoiturage annonce = this.annonceCovoiturageRepository
                        .findById(nouvelleResa.getIdCovoiturage())
                                .orElseThrow(NotFoundException::new);

        this.verifierNbPlaces(annonce);

        resa.setPassager(pax);
        resa.setAnnonceCovoiturage(annonce);
        return this.reservationCovoiturageRepository.save(resa);
    }

    public ReservationCovoiturage afficherUneReservation(Integer id_resa) throws NotFoundException {
        return this.reservationCovoiturageRepository.findById(id_resa)
                .orElseThrow(NotFoundException::new);
    }

    public List<ReservationCovoiturage> listerToutesReservations(PageRequest pr) {
        return this.reservationCovoiturageRepository.findAll(pr).toList();
    }
}
