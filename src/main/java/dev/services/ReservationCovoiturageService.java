package dev.services;

import dev.dto.mappers.ListeReservationMapper;
import dev.dto.mappers.ReservationDetailMapper;
import dev.dto.reservation.covoiturage.*;
import dev.entites.AnnonceCovoiturage;
import dev.entites.Collaborateur;
import dev.entites.reservation.ReservationCovoiturage;
import dev.exception.*;
import dev.repositories.AnnonceCovoiturageRepository;
import dev.repositories.CollaborateurRepository;
import dev.repositories.ReservationCovoiturageRepository;
import dev.utils.Annonce;
import dev.utils.Email;
import dev.utils.Resa;
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
     * Vérifie que l'annonce est ouverte et le nb de places restantes suffisant, lance l'exception sinon
     * @param annonce
     * @throws CovoiturageCompletException
     */
    private AnnonceCovoiturage verifierAnnonceValide(AnnonceCovoiturage annonce) throws CovoiturageCompletException, CovoiturageIndisponibleException {
        if(!annonce.getStatut().equals(Annonce.OUVERT.getVal())) throw new CovoiturageIndisponibleException("Ce covoiturage n'est pas ouvert à la réservation");
        if(annonce.getNbPlaces() <= this.reservationCovoiturageRepository.calculerNbPlacesReservees(annonce.getId())){
            throw new CovoiturageCompletException("Plus de places disponibles sur ce covoiturage");
        }
        return annonce;
    }

    /**
     * Renvoie la liste si non vide, lance une exception sinon
     * @param l
     * @return
     * @throws ListeVideException
     */
    private List<ReservationCovoiturage> safeReturnList(List<ReservationCovoiturage> l) throws ListeVideException {
        if(l.isEmpty()) throw new ListeVideException("Pas de réservation covoiturage à renvoyer");
        return l;
    }

    /**
     * Renvoie la liste si non vide, lance une exception sinon
     * @param l
     * @return
     * @throws ListeVideException
     */
    private List<ReservationCovoiturageSimpleDto> safeReturnListDto(List<ReservationCovoiturageSimpleDto> l) throws ListeVideException {
        if(l.isEmpty()) throw new ListeVideException("Pas de réservation covoiturage à renvoyer");
        return l;
    }

    /**
     * Insère une résa covoiturage en base
     * @param resa
     * @return la résa enregistrée
     * @throws NotFoundException
     */
    public ReservationCovoiturage reserverCovoiturage(CreerReservationCovoiturageDto resa) throws NotFoundException {
        AnnonceCovoiturage annonce = this.verifierAnnonceValide(
                this.annonceCovoiturageRepository
                .findById(resa.getIdCovoiturage())
                .orElseThrow(NotFoundException::new));
        Collaborateur collab = this.utilisateurRepository
                .findById(resa.getIdCollaborateur())
                .orElseThrow(NotFoundException::new);

        ReservationCovoiturage nouvelleResa = new ReservationCovoiturage();
        nouvelleResa.setAnnonceCovoiturage(annonce);
        nouvelleResa.setPassager(collab);
        nouvelleResa.setStatut(Resa.ACTIF.getVal());
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

        resa.setStatut(Resa.ANNULE.getVal());
        this.reservationCovoiturageRepository.save(resa);

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

        AnnonceCovoiturage annonce = this.verifierAnnonceValide(
                this.annonceCovoiturageRepository
                        .findById(nouvelleResa.getIdCovoiturage())
                                .orElseThrow(NotFoundException::new));

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
    public List<ReservationCovoiturage> listerToutesReservations(PageRequest pr) throws ListeVideException {
        return this.safeReturnList(this.reservationCovoiturageRepository.findAll(pr).toList());
    }

    /**
     * Renvoie la liste de toutes les résas
     * @return
     */
    public List<ReservationCovoiturage> lister() throws ListeVideException {
        return this.safeReturnList(this.reservationCovoiturageRepository.findAll());
    }

    /**
     * Renvoie la liste des résas à venir d'un utilisateur
     * @param id_utilisateur
     * @return
     */
    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateurAvenir(Integer id_utilisateur) throws ListeVideException {
        return this.safeReturnListDto(this.listeReservationMapper
                .map(this.reservationCovoiturageRepository
                .findByPassagerIdAndAnnonceCovoiturageDateHeureDepartGreaterThanEqual(id_utilisateur,
                        LocalDate.now().atStartOfDay())));
    }

    /**
     *  Renvoie la liste des résas passées d'un utilisateur
     * @param id_utilisateur
     * @return
     */
    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateurHisto(Integer id_utilisateur) throws ListeVideException {
        return this.safeReturnListDto(this.listeReservationMapper
                .map(this.reservationCovoiturageRepository
                .findByPassagerIdAndAnnonceCovoiturageDateHeureDepartLessThan(id_utilisateur,
                        LocalDate.now().atStartOfDay())));
    }

    /**
     * Renvoie la liste de toutes les résas d'un utilisateur
     * @param id_utilisateur
     * @return
     */
    public List<ReservationCovoiturageSimpleDto> afficherReservationsParUtilisateur(Integer id_utilisateur) throws ListeVideException {
        return this.safeReturnListDto(this.listeReservationMapper
                .map(this.reservationCovoiturageRepository
                        .listerReservationsParUtilisateur(id_utilisateur)));
    }

    /**
     * Lister les resas ACTIVES d'un passager
     * @param id
     * @return
     * @throws ListeVideException
     */
    public List<ReservationCovoiturageSimpleDto> listerParUtilisateurActive(Integer id) throws ListeVideException{
        return this.safeReturnListDto(this.listeReservationMapper
                .map(this.reservationCovoiturageRepository
                        .findByPassagerIdAndStatutLike(id, Resa.ACTIF.getVal())));
    }
    /**
     * Lister les resas ARCHIVE d'un passager
     * @param id
     * @return
     * @throws ListeVideException
     */
    public List<ReservationCovoiturageSimpleDto> listerParUtilisateurArchive(Integer id) throws ListeVideException{
        return this.safeReturnListDto(this.listeReservationMapper
                .map(this.reservationCovoiturageRepository
                        .findByPassagerIdAndStatutLike(id, Resa.ARCHIVE.getVal())));
    }

    /**
     * Lister les resas ANNULEES d'un passager
     * @param id
     * @return
     * @throws ListeVideException
     */
    public List<ReservationCovoiturageSimpleDto> listerParUtilisateurAnnule(Integer id) throws ListeVideException{
        return this.safeReturnListDto(this.listeReservationMapper
                .map(this.reservationCovoiturageRepository
                        .findByPassagerIdAndStatutLike(id, Resa.ANNULE.getVal())));
    }


}
