package dev.services;

import dev.dto.mappers.ListeReservationMapper;
import dev.dto.mappers.ReservationDetailMapper;
import dev.dto.reservation.covoiturage.*;
import dev.entites.AnnonceCovoiturage;
import dev.entites.Collaborateur;
import dev.entites.reservation.ReservationCovoiturage;
import dev.exception.CovoiturageCompletException;
import dev.exception.DateDepasseeException;
import dev.exception.NotFoundException;
import dev.repositories.AnnonceCovoiturageRepository;
import dev.repositories.CollaborateurRepository;
import dev.repositories.ReservationCovoiturageRepository;
import dev.utils.Email;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationCovoiturageService {

    private ReservationCovoiturageRepository reservationCovoiturageRepository;
    private AnnonceCovoiturageRepository annonceCovoiturageRepository;
    private CollaborateurRepository utilisateurRepository;
    private ReservationDetailMapper reservationMapper;
    private ListeReservationMapper listeReservationMapper;
    private Email email;

    public ReservationCovoiturageService(ReservationCovoiturageRepository reservationCovoiturageRepository, AnnonceCovoiturageRepository annonceCovoiturageRepository, CollaborateurRepository utilisateurRepository, ReservationDetailMapper reservationMapper, ListeReservationMapper listeReservationMapper, Email email) {
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.reservationMapper = reservationMapper;
        this.listeReservationMapper = listeReservationMapper;
        this.email = email;
    }

    /**
     * Vérifie le nb de places restantes, lance l'exception si complet
     * @param annonce
     * @throws CovoiturageCompletException
     */
    private void verifierNbPlaces(AnnonceCovoiturage annonce) throws CovoiturageCompletException {
        if(annonce.getNbPlaces() <= this.reservationCovoiturageRepository.calculerNbPlacesReservees(annonce.getId())){
            throw new CovoiturageCompletException("Plus de places disponibles sur ce covoiturage");
        }
    }

    /**
     * Insère une résa covoiturage en base
     * @param resa
     * @return la résa enregistrée
     * @throws NotFoundException
     */
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

    /**
     * Annuler une résa
     * @param id_resa
     */
    public void annulerReservationCovoiturage(Integer id_resa){
        this.supprimerReservationCovoiturageId("Annulation à l'initiative du passager", id_resa);
    }

    /**
     * Annuler une résa : NotFound et raison d'annulation
     * @param raison
     * @param id_resa
     */
    public void supprimerReservationCovoiturageId(String raison, Integer id_resa) {
        ReservationCovoiturage resa = this.reservationCovoiturageRepository.findById(id_resa)
                .orElseThrow(NotFoundException::new);
        this.supprimerReservationCovoiturageResa(raison, resa);
    }

    /**
     * Annuler une résa : DateDepassée, deleteById et envoi d'emails
     * @param raison
     * @param resa
     */
    public void supprimerReservationCovoiturageResa(String raison, ReservationCovoiturage resa){
        if(resa.getAnnonceCovoiturage()
                .getDateHeureDepart()
                .isBefore(LocalDateTime.now())) {
            throw new DateDepasseeException();
        }

        this.reservationCovoiturageRepository.deleteById(resa.getId());

        this.email.envoyerEmail(raison, resa);
    }

    /**
     * Ecraser une réservation en base
     * @param nouvelleResa
     * @return résa
     * @throws NotFoundException
     */
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

    /**
     * Renvoie le dto détaillé d'une réservation
     * @param id_resa
     * @return dto détaillé
     * @throws NotFoundException
     */
    public ReservationCovoiturageDetailDto afficherUneReservation(Integer id_resa) throws NotFoundException {
        return this.reservationMapper.toReservationCovoiturageDetailDto(
                this.reservationCovoiturageRepository.findById(id_resa)
                .orElseThrow(NotFoundException::new));
    }

    /**
     * Renvoie la liste paginée de toutes les résas en base
     * @param pr
     * @return liste des résas
     */
    public List<ReservationCovoiturage> listerToutesReservations(PageRequest pr) {
        return this.reservationCovoiturageRepository.findAll(pr).toList();
    }

    /**
     * Renvoie la liste de toutes les résas
     * @return
     */
    public List<ReservationCovoiturage> lister(){
        return this.reservationCovoiturageRepository.findAll();
    }

    /**
     * Renvoie la liste des résas à venir d'un utilisateur
     * @param id_utilisateur
     * @return
     */
    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateurAvenir(Integer id_utilisateur) {
        return this.listeReservationMapper.map(this.reservationCovoiturageRepository
                .findByPassagerIdAndAnnonceCovoiturageDateHeureDepartGreaterThanEqual(id_utilisateur, LocalDate.now().atStartOfDay()));
    }

    /**
     *  Renvoie la liste des résas passées d'un utilisateur
     * @param id_utilisateur
     * @return
     */
    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateurHisto(Integer id_utilisateur) {
        return this.listeReservationMapper.map(this.reservationCovoiturageRepository
                .findByPassagerIdAndAnnonceCovoiturageDateHeureDepartLessThan(id_utilisateur, LocalDate.now().atStartOfDay()));
    }

    /**
     * Renvoie la liste de toutes les résas d'un utilisateur
     * @param id_utilisateur
     * @return
     */
    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateur(Integer id_utilisateur) {
        return this.listeReservationMapper.map(this.reservationCovoiturageRepository.listerReservationsParUtilisateur(id_utilisateur));
    }

}
