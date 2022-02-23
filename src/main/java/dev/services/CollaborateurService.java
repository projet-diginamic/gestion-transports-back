package dev.services;

import dev.entites.Collaborateur;
import dev.exception.NotFoundException;
import dev.repositories.CollaborateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service collaborateurs
 */
@Service
public class CollaborateurService {

    private CollaborateurRepository repo;

    public CollaborateurService(CollaborateurRepository utilisateurRepository) {
        this.repo = utilisateurRepository;
    }

    /**
     * Insère un collaborateur en base
     * @param u
     * @return
     */
    public Integer insererUtilisateur(Collaborateur u) {
        this.repo.save(u);
        return u.getId();
    }

    /**
     * Renvoie un collaborateur
     * @param id
     * @return
     */
    public Collaborateur afficherUtilisateur(Integer id) {
        return this.repo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Renvoie true si le collaborateur est admin
     * @param id
     * @return
     */
    public boolean isAdmin(Integer id) {
        return this.repo.findById(id)
                .orElseThrow(NotFoundException::new)
                .getIsAdmin();
    }

    /**
     * Liste de tous les collaborateurs
     * @return
     */
    public List<Collaborateur> lister() {
        return this.repo.findAll();
    }
}
