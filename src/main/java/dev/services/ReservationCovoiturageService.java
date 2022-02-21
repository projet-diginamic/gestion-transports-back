package dev.services;

import dev.dto.mappers.ListeReservationMapper;
import dev.dto.mappers.ReservationDetailMapper;
import dev.dto.reservation.covoiturage.*;
import dev.dto.mappers.ReservationMapper;
import dev.entites.AnnonceCovoiturage;
import dev.entites.Collaborateur;
import dev.entites.Utilisateur;
import dev.entites.reservation.ReservationCovoiturage;
import dev.exception.CovoiturageCompletException;
import dev.exception.DateDepasseeException;
import dev.exception.NotFoundException;
import dev.repositories.AnnonceCovoiturageRepository;
import dev.repositories.CollaborateurRepository;
import dev.repositories.ReservationCovoiturageRepository;
import dev.repositories.UtilisateurRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationCovoiturageService {

    private ReservationCovoiturageRepository reservationCovoiturageRepository;
    private AnnonceCovoiturageRepository annonceCovoiturageRepository;
    private CollaborateurRepository utilisateurRepository;
    private ReservationDetailMapper reservationMapper;
    private ListeReservationMapper listeReservationMapper;

    public ReservationCovoiturageService(ReservationCovoiturageRepository reservationCovoiturageRepository, AnnonceCovoiturageRepository annonceCovoiturageRepository, CollaborateurRepository utilisateurRepository, ReservationDetailMapper reservationMapper, ListeReservationMapper listeReservationMapper) {
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.reservationMapper = reservationMapper;
        this.listeReservationMapper = listeReservationMapper;
    }

    private void verifierNbPlaces(AnnonceCovoiturage annonce) throws CovoiturageCompletException {
        if(annonce.getNbPlaces() <= this.reservationCovoiturageRepository.calculerNbPlacesReservees(annonce.getId())){
            throw new CovoiturageCompletException("Plus de places disponibles sur ce covoiturage");
        }
    }

    private void verifierDate(Integer id) throws NotFoundException, DateDepasseeException{
        if(this.reservationCovoiturageRepository.findById(id)
                .orElseThrow(NotFoundException::new)
                .getAnnonceCovoiturage()
                .getDateHeureDepart()
                .isBefore(LocalDateTime.now())){
            throw new DateDepasseeException();
        }
    }

    public ReservationCovoiturage reserverCovoiturage(CreerReservationCovoiturageDto resa) throws NotFoundException {
        AnnonceCovoiturage annonce = this.annonceCovoiturageRepository
                .findById(resa.getIdCovoiturage())
                .orElseThrow(NotFoundException::new);
        Collaborateur collab = this.utilisateurRepository
                .findById(resa.getIdCollaborateur())
                .orElseThrow(NotFoundException::new);

        this.verifierNbPlaces(annonce);

        ReservationCovoiturage nouvelleResa = new ReservationCovoiturage();
        nouvelleResa.setAnnonceCovoiturage(annonce);
        nouvelleResa.setPassager(collab);

        return this.reservationCovoiturageRepository.save(nouvelleResa);
    }

    public void supprimerReservationCovoiturage(Integer id_resa){
        this.verifierDate(id_resa);
        this.reservationCovoiturageRepository.deleteById(id_resa);
    }

    public ReservationCovoiturage modifierReservationCovoiturage(ModifierReservationCovoiturageDto nouvelleResa) throws NotFoundException{
        ReservationCovoiturage resa = this.reservationCovoiturageRepository
                .findById(nouvelleResa.getIdResa())
                .orElseThrow(NotFoundException::new);

        Collaborateur pax = this.utilisateurRepository
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

    public ReservationCovoiturageDetailDto afficherUneReservation(Integer id_resa) throws NotFoundException {
        return this.reservationMapper.toReservationCovoiturageDetailDto(
                this.reservationCovoiturageRepository.findById(id_resa)
                .orElseThrow(NotFoundException::new));
    }

    public List<ReservationCovoiturage> listerToutesReservations(PageRequest pr) {
        return this.reservationCovoiturageRepository.findAll(pr).toList();
    }

    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateurAvenir(Integer id_utilisateur) {
        return this.listeReservationMapper.map(this.reservationCovoiturageRepository
                .findByPassagerIdAndAnnonceCovoiturageDateHeureDepartGreaterThanEqual(id_utilisateur, LocalDate.now().atStartOfDay()));
    }

    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateurHisto(Integer id_utilisateur) {
        return this.listeReservationMapper.map(this.reservationCovoiturageRepository
                .findByPassagerIdAndAnnonceCovoiturageDateHeureDepartLessThan(id_utilisateur, LocalDate.now().atStartOfDay()));
    }

    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateur(Integer id_utilisateur) {
        return this.listeReservationMapper.map(this.reservationCovoiturageRepository.listerReservationsParUtilisateur(id_utilisateur));
    }

}
