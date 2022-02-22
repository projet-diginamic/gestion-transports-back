package dev.services;

import dev.entites.Collaborateur;
import dev.exception.NotFoundException;
import dev.repositories.CollaborateurRepository;
import org.springframework.stereotype.Service;

@Service
public class CollaborateurService {

    private CollaborateurRepository utilisateurRepository;

    public CollaborateurService(CollaborateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Integer insererUtilisateur(Collaborateur u) {
        this.utilisateurRepository.save(u);
        return u.getId();
    }

    public Collaborateur afficherUtilisateur(Integer id) {
        return this.utilisateurRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public boolean isAdmin(Integer id) {
        return this.utilisateurRepository.findById(id)
                .orElseThrow(NotFoundException::new)
                .getIsAdmin();
    }
}
