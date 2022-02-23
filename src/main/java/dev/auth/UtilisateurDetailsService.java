package dev.auth;

import dev.entites.Utilisateur;
import dev.exception.NotFoundException;
import dev.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        Utilisateur user = this.utilisateurRepository.findByEmail(username)
                .orElseThrow(NotFoundException::new);
        return new UtilisateurPrincipal(user);
    }
}
