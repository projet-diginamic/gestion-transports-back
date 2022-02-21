package dev.dto.reservation.covoiturage;

import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;

import java.time.LocalDate;


public class ReqCovoit {

    private Integer adresseDepart;
    private Integer adresseArrivee;
    private LocalDate date;

    public Integer getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(Integer adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public Integer getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(Integer adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateHeureDepart) {
        this.date = dateHeureDepart;
    }
}
