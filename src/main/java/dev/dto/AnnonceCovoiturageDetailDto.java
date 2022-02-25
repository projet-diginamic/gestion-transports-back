package dev.dto;

import dev.entites.Collaborateur;
import dev.entites.Utilisateur;
import dev.entites.VehiculeCovoiturage;
import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;

import java.time.LocalDateTime;
import java.util.List;

public class AnnonceCovoiturageDetailDto {

    private Integer id;

    private LocalDateTime dateHeureDepart;

    private Utilisateur organisateur;

    private AdresseDepart adresseDepart;

    private AdresseArrivee adresseArrivee;

    private VehiculeCovoiturage vehicule;

    private Integer nbPlaces;

    private Integer nbResas;

    private String statut;

    private List<Collaborateur> passagers;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public Utilisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Utilisateur organisateur) {
        this.organisateur = organisateur;
    }

    public AdresseDepart getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(AdresseDepart adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public AdresseArrivee getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(AdresseArrivee adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public VehiculeCovoiturage getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeCovoiturage vehicule) {
        this.vehicule = vehicule;
    }

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public Integer getNbResas() {
        return nbResas;
    }

    public void setNbResas(Integer nbResas) {
        this.nbResas = nbResas;
    }

    public List<Collaborateur> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<Collaborateur> passagers) {
        this.passagers = passagers;
    }
}
