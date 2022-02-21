package dev.services;

import dev.entites.AnnonceCovoiturage;
import dev.exception.CovoiturageCompletException;
import dev.exception.NotFoundException;
import dev.repositories.AnnonceCovoiturageRepository;
import dev.repositories.ReservationCovoiturageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class AnnonceCovoiturageService {

    private AnnonceCovoiturageRepository annonceCovoiturageRepository;
    private ReservationCovoiturageRepository reservationCovoiturageRepository;

    public AnnonceCovoiturageService(AnnonceCovoiturageRepository annonceCovoiturageRepository, ReservationCovoiturageRepository reservationCovoiturageRepository){
        super();
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
        this.reservationCovoiturageRepository = reservationCovoiturageRepository;
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

    public void supprimerCovoiturage (Integer id) {
        this.annonceCovoiturageRepository.deleteById(id);
    }

    public void modifierAnnonce(AnnonceCovoiturage nouv) throws CovoiturageCompletException {
        this.annonceCovoiturageRepository.save(nouv);
        this.verifierNbPlaces(nouv);
    }

    public List<AnnonceCovoiturage> lister() {
        return this.annonceCovoiturageRepository.findAll();
    }
}