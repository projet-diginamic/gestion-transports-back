package dev.services;

import dev.dto.reservation.covoiturage.ReqCovoit;
import dev.entites.AnnonceCovoiturage;
import dev.exception.CovoiturageCompletException;
import dev.exception.DateDepasseeException;
import dev.exception.NotFoundException;
import dev.repositories.AnnonceCovoiturageRepository;
import dev.repositories.ReservationCovoiturageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public
class AnnonceCovoiturageService {

    private AnnonceCovoiturageRepository annonceCovoiturageRepository;
    private ReservationCovoiturageRepository reservationCovoiturageRepository;
    private ReservationCovoiturageService reservationCovoiturageService;

    public AnnonceCovoiturageService(AnnonceCovoiturageRepository annonceCovoiturageRepository, ReservationCovoiturageRepository reservationCovoiturageRepository, ReservationCovoiturageService reservationCovoiturageService){
        super();
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
        this.reservationCovoiturageService = reservationCovoiturageService;
    }

    private void verifierNbPlaces(AnnonceCovoiturage annonce) throws CovoiturageCompletException {
        if(annonce.getNbPlaces() <= this.reservationCovoiturageRepository.calculerNbPlacesReservees(annonce.getId())){
            throw new CovoiturageCompletException("Plus de places disponibles sur ce covoiturage");
        }
    }

    public List<AnnonceCovoiturage> listerCovoiturages(PageRequest pr){
        return this.annonceCovoiturageRepository.findAll(pr).toList();
    }

    public AnnonceCovoiturage recupererCovoiturage(Integer id) throws NotFoundException{
        return this.annonceCovoiturageRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public AnnonceCovoiturage publierAnnonce(AnnonceCovoiturage annonce) {
        return this.annonceCovoiturageRepository.save(annonce);
    }

    public void supprimerCovoiturage (Integer id) throws NotFoundException, DateDepasseeException{
        AnnonceCovoiturage annonce = this.annonceCovoiturageRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        if(annonce.getDateHeureDepart().isBefore(LocalDateTime.now())){
            throw new DateDepasseeException();
        }

        annonce.getResas().forEach(x -> this.reservationCovoiturageService
                .supprimerReservationCovoiturageResa("Annulation du covoiturage", x));

        this.annonceCovoiturageRepository.deleteById(id);
    }

    public void modifierAnnonce(AnnonceCovoiturage nouv) throws CovoiturageCompletException {
        this.verifierNbPlaces(nouv);
        this.annonceCovoiturageRepository.save(nouv);
    }

    public List<AnnonceCovoiturage> lister() {
        return this.annonceCovoiturageRepository.findAll();
    }

    public List<AnnonceCovoiturage> listerAnnoncesOrga(Integer id) {
        return this.annonceCovoiturageRepository.findByOrganisateurId(id);
    }

    public List<AnnonceCovoiturage> listerAnnoncesOrgaAvenir(Integer id) {
        return this.annonceCovoiturageRepository.findByOrganisateurIdAndDateHeureDepartGreaterThanEqual(id, LocalDate.now().atStartOfDay());
    }

    public List<AnnonceCovoiturage> listerAnnoncesOrgaHisto(Integer id) {
        return this.annonceCovoiturageRepository.findByOrganisateurIdAndDateHeureDepartLessThan(id, LocalDate.now().atStartOfDay());
    }

    public List<AnnonceCovoiturage> rechercher(ReqCovoit req) {
        return this.annonceCovoiturageRepository.rechercher(req.getAdresseDepart(), req.getAdresseArrivee(), req.getDate());
    }
}