package dev.services;

import dev.entites.AnnonceCovoiturage;
import dev.repositories.AnnonceCovoiturageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class AnnonceCovoiturageService {

    private AnnonceCovoiturageRepository annonceCovoiturageRepository;

    public AnnonceCovoiturageService(AnnonceCovoiturageRepository annonceCovoiturageRepository){
        super();
        this.annonceCovoiturageRepository = annonceCovoiturageRepository;
    }

    public List<AnnonceCovoiturage> listerCovoiturages(PageRequest pr){
        return this.annonceCovoiturageRepository.findAll(pr).toList();
    }

    public AnnonceCovoiturage recupererCovoiturage(Integer id) throws Exception{
        return this.annonceCovoiturageRepository.findById(id)
                .orElseThrow(Exception::new);
    }

    public AnnonceCovoiturage publierAnnonce(AnnonceCovoiturage annonce) {
        return this.annonceCovoiturageRepository.save(annonce);
    }

    public void supprimerCovoiturage (Integer id) {
        this.annonceCovoiturageRepository.deleteById(id);
    }

    public AnnonceCovoiturage modifierAnnonce(AnnonceCovoiturage annonce) throws Exception {
        this.annonceCovoiturageRepository
                .findById(annonce.getId())
                .orElseThrow(Exception::new);
        this.supprimerCovoiturage(annonce.getId());
        return this.publierAnnonce(annonce);
    }

    public List<AnnonceCovoiturage> lister() {
        return this.annonceCovoiturageRepository.findAll();
    }
}