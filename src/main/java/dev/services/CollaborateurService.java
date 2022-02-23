package dev.services;

import dev.entites.Collaborateur;
import dev.exception.NotFoundException;
import dev.repositories.CollaborateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollaborateurService {

    private CollaborateurRepository repo;

    public CollaborateurService(CollaborateurRepository utilisateurRepository) {
        this.repo = utilisateurRepository;
    }

    public Integer insererUtilisateur(Collaborateur u) {
        this.repo.save(u);
        return u.getId();
    }

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
