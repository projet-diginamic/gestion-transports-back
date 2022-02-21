package dev.dto.reservation.covoiturage;

import java.time.LocalDate;


public class ReqCovoit {

    private String adresseDepart;
    private String adresseArrivee;
    private LocalDate date;

    public String getAdresseDepart() {
        return adresseDepart;
    }

    public void setAdresseDepart(String adresseDepart) {
        this.adresseDepart = adresseDepart;
    }

    public String getAdresseArrivee() {
        return adresseArrivee;
    }

    public void setAdresseArrivee(String adresseArrivee) {
        this.adresseArrivee = adresseArrivee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateHeureDepart) {
        this.date = dateHeureDepart;
    }
}
