package dev.dto;


import dev.entites.VehiculeCovoiturage;
import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;

import java.time.LocalDateTime;

public class AnnonceCovoiturageDto {

    private LocalDateTime dateHeureDepart;

    private Integer organisateur;

    private AdresseDepart adresseDepart;

    private AdresseArrivee adresseArrivee;

    private VehiculeCovoiturage vehicule;

    private Integer nbPlaces;

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public Integer getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Integer organisateur) {
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
}
