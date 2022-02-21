package dev.dto.reservation.covoiturage;

import dev.dto.UtilisateurDto;
import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;
import dev.entites.reservation.ReservationCovoiturage;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationCovoiturageDetailDto {

    private Integer id;

    private LocalDateTime dateHeureDepart;

    private LocalDateTime dateHeureArrivee;

    private AdresseDepart adresseDepart;

    private AdresseArrivee adresseArrivee;

    private List<UtilisateurDto> passagers;

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

    public LocalDateTime getDateHeureArrivee() {
        return dateHeureArrivee;
    }

    public void setDateHeureArrivee(LocalDateTime dateHeureArrivee) {
        this.dateHeureArrivee = dateHeureArrivee;
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

    public List<UtilisateurDto> getPassagers() {
        return passagers;
    }

    public void setPassagers(List<UtilisateurDto> passagers) {
        this.passagers = passagers;
    }
}
