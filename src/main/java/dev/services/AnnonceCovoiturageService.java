package dev.services;

import dev.dto.AnnonceCovoiturageDetailDto;
import dev.dto.AnnonceCovoiturageDto;
import dev.dto.mappers.ListeAnnonceMapper;
import dev.dto.reservation.covoiturage.ReqCovoit;
import dev.entites.AnnonceCovoiturage;
import dev.exception.CovoiturageCompletException;
import dev.exception.DateDepasseeException;
import dev.exception.ListeVideException;
import dev.exception.NotFoundException;
import dev.repositories.*;
import dev.utils.Annonce;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service des annonces covoiturage
 */
@Service
public class AnnonceCovoiturageService {

    private AnnonceCovoiturageRepository annonceCovoiturageRepository;
    private ReservationCovoiturageRepository reservationCovoiturageRepository;
    private ReservationCovoiturageService reservationCovoiturageService;
    private UtilisateurRepository utilisateurRepository;
    private AdresseDepartRepository adresseDepartRepository;
    private AdresseArriveeRepository adresseArriveeRepository;
    private VehiculeCovoiturageRepository vehiculeCovoiturageRepository;
    private ListeAnnonceMapper listeAnnonceMapper;

    public AnnonceCovoiturageService(AnnonceCovoiturageRepository annonceCovoiturageRepository, ReservationCovoiturageRepository reservationCovoiturageRepository, ReservationCovoiturageService reservationCovoiturageService, UtilisateurRepository utilisateurRepository, AdresseDepartRepository adresseDepartRepository, AdresseArriveeRepository adresseArriveeRepository, VehiculeCovoiturageRepository vehiculeCovoiturageRepository, ListeAnnonceMapper listeAnnonceMapper){
        super();
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
        this.reservationCovoiturageService = reservationCovoiturageService;
        this.utilisateurRepository = utilisateurRepository;
        this.adresseDepartRepository = adresseDepartRepository;
        this.adresseArriveeRepository = adresseArriveeRepository;
        this.vehiculeCovoiturageRepository = vehiculeCovoiturageRepository;
        this.listeAnnonceMapper = listeAnnonceMapper;
    }

    /**
     * Vérifie qu'il reste des places sur un covoiturages, lance une exception le cas échéant
     * @param annonce
     * @throws CovoiturageCompletException
     */
    private void verifierNbPlaces(AnnonceCovoiturage annonce) throws CovoiturageCompletException {
        if(annonce.getNbPlaces() <= this.reservationCovoiturageRepository.calculerNbPlacesReservees(annonce.getId())){
            throw new CovoiturageCompletException("Plus de places disponibles sur ce covoiturage");
        }
    }

    /**
     * Renvoie une liste si elle n'est pas vide, lance exception sinon
     * @param
     * @return List<AnnonceCovoiturage>
     * @throws ListeVideException
     */
    private List<AnnonceCovoiturage> safeReturnList(List<AnnonceCovoiturage> l) throws ListeVideException {
        if(l.isEmpty()) throw new ListeVideException("Aucune annonce covoiturage à renvoyer");
        return l;
    }

    /**
     * Renvoie une liste si elle n'est pas vide, lance exception sinon
     * @param
     * @return List<AnnonceCovoiturage>
     * @throws ListeVideException
     */
    private List<AnnonceCovoiturageDetailDto> safeReturnListDto(List<AnnonceCovoiturageDetailDto> l) throws ListeVideException {
        if(l.isEmpty()) throw new ListeVideException("Aucune annonce covoiturage à renvoyer");
        return l;
    }

    /**
     * Renvoie la liste de toutes les annonces de covoiturages, passées et à venir
     * @param  pr pagerequest
     * @return Liste d'annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerCovoiturages(PageRequest pr) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository.findAll(pr).toList()));
    }

    /**
     * Renvoie l'annonce d'id correspondant
     * @param id
     * @return AnnonceCovoiturage
     * @throws NotFoundException
     */
    public AnnonceCovoiturage recupererCovoiturage(Integer id) throws NotFoundException{
        return this.annonceCovoiturageRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Insère en base une annonce correspondant au dto fourni
     * @param  dto annonce covoiturage
     * @return l'annonce nouvellement créée
     */
    public AnnonceCovoiturage publierAnnonce(AnnonceCovoiturageDto dto) {
        AnnonceCovoiturage a = new AnnonceCovoiturage();
        a.setOrganisateur(this.utilisateurRepository
                .findById(dto.getOrganisateur())
                .orElseThrow(NotFoundException::new));

        a.setAdresseDepart(this.adresseDepartRepository
                .save(dto.getAdresseDepart()));

        a.setAdresseArrivee(this.adresseArriveeRepository
                .save(dto.getAdresseArrivee()));

        a.setDateHeureDepart(dto.getDateHeureDepart());
        a.setNbPlaces(dto.getNbPlaces());
        a.setVehicule(this.vehiculeCovoiturageRepository
                .save(dto.getVehicule()));
        a.setStatut(Annonce.OUVERT.getVal());
        return this.annonceCovoiturageRepository.save(a);
    }

    /**
     * Supprime l'annonce correspondant à l'id
     * @param id
     * @return l'annonce supprimée
     * @throws NotFoundException
     * @throws DateDepasseeException
     */
    public AnnonceCovoiturage supprimerCovoiturage (Integer id) throws NotFoundException, DateDepasseeException{
        AnnonceCovoiturage annonce = this.annonceCovoiturageRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        if(annonce.getDateHeureDepart().isBefore(LocalDateTime.now())){
            throw new DateDepasseeException();
        }

        annonce.getResas().forEach(x -> this.reservationCovoiturageService
                .supprimerReservationCovoiturageResa("Annulation du covoiturage", x));

        annonce.setStatut(Annonce.ANNULE.getVal());
        this.annonceCovoiturageRepository.save(annonce);
        return annonce;
    }

    /**
     * écrase une annonce en base (l'id doit être existant)
     * @param nouv
     * @throws CovoiturageCompletException
     */
    public void modifierAnnonce(AnnonceCovoiturage nouv) throws CovoiturageCompletException {
        this.verifierNbPlaces(nouv);
        this.annonceCovoiturageRepository.save(nouv);
    }

    /**
     * findAll des annonces
     * @return
     */
    public List<AnnonceCovoiturageDetailDto> lister() throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository.findAll()));
    }

    /**
     * Renvoie toutes les annonces d'un organisateur
     * @param id
     * @return Liste des annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrga(Integer id) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository.findByOrganisateurId(id)));
    }

    /**
     * Renvoie toutes les annonces à venir (> date du jour) d'un organisateur
     * @param id
     * @return Liste des annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrgaAvenir(Integer id) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                            .findByOrganisateurIdAndDateHeureDepartGreaterThanEqual(id,
                                LocalDate.now().atStartOfDay())));
    }

    /**
     * Renvoie toutes les annonces passées (< date du jour) d'un organisateur
     * @param id
     * @return Liste des annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrgaHisto(Integer id) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                            .findByOrganisateurIdAndDateHeureDepartLessThan(id,
                                LocalDate.now().atStartOfDay())));
    }

    /**
     * Recherche filtrée par paramètres d'annonces de covoiturage
     * @param req covoiturage : adresse départ (nullable), adresse arrivée (nullable), date (nullable)
     * @return Liste des annonces satisfaisant les critères
     */
    public List<AnnonceCovoiturageDetailDto> rechercher(ReqCovoit req) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                            .rechercher(req.getAdresseDepart(),
                                req.getAdresseArrivee(),
                                req.getDate())));
    }

    /**
     * Lister les annonces statut OUVERT
     * @return
     * @throws ListeVideException
     */
    public List<AnnonceCovoiturageDetailDto> listerActives() throws ListeVideException{
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                            .findByStatutLike(Annonce.OUVERT.getVal())));
    }

    /**
     * Lister les annonces statut ARCHIVE
     * @return
     * @throws ListeVideException
     */
    public List<AnnonceCovoiturageDetailDto> listerArchives() throws ListeVideException{
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                            .findByStatutLike(Annonce.ARCHIVE.getVal())));
    }

    /**
     * Lister les annonces statut ANNULE
     * @return
     * @throws ListeVideException
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnule() throws ListeVideException{
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                            .findByStatutLike(Annonce.ANNULE.getVal())));
    }

    /**
     * Renvoie toutes les annonces OUVERT d'un organisateur
     * @param id
     * @return Liste des annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrgaActif(Integer id) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                                .findByOrganisateurIdAndStatutLike(id, Annonce.OUVERT.getVal())));
    }

    /**
     * Renvoie toutes les annonces ARCHIVE d'un organisateur
     * @param id
     * @return Liste des annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrgaArchive(Integer id) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                                .findByOrganisateurIdAndStatutLike(id, Annonce.ARCHIVE.getVal())));
    }

    /**
     * Renvoie toutes les annonces ANNULE d'un organisateur
     * @param id
     * @return Liste des annonces
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrgaAnnule(Integer id) throws ListeVideException {
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                                .findByOrganisateurIdAndStatutLike(id, Annonce.ANNULE.getVal())));
    }

    /**
     * Renvoie toutes les annonces ARCHIVE ET ANNULE d'un organisateur
     * @param id
     * @return
     * @throws ListeVideException
     */
    public List<AnnonceCovoiturageDetailDto> listerAnnoncesOrgaAA(Integer id) throws ListeVideException{
        return this.safeReturnListDto(
                this.listeAnnonceMapper.map(
                        this.annonceCovoiturageRepository
                                .findByOrganisateurIdAndStatutNotLike(id, Annonce.OUVERT.getVal())));
    }
}