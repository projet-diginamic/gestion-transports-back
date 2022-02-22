package dev.dto.reservation.covoiturage;

import dev.exception.entites.adresse.AdresseArrivee;
import dev.exception.entites.adresse.AdresseDepart;

import java.time.LocalDateTime;

public class ReservationCovoiturageSimpleDto {

    private Integer id;

    private LocalDateTime dateHeureDepart;

    private AdresseArrivee adresseArrivee;

    private AdresseDepart adresseDepart;

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
