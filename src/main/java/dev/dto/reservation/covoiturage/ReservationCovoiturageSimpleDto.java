package dev.dto.reservation.covoiturage;

import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;

import java.time.LocalDateTime;

public class ReservationCovoiturageSimpleDto {

    private Integer id;

    private LocalDateTime dateHeureDepart;

    private AdresseArrivee adresseArrivee;

    private AdresseDepart adresseDepart;

    private String statut;

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

    public AdresseArrivee getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(AdresseArrivee adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public AdresseDepart getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(AdresseDepart adresseDepart) {
        this.adresseDepart = adresseDepart;
    }
}
