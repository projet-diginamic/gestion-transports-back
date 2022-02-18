package dev.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class AnnonceCovoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateHeureDepart;

    private AdresseDepart adresseDepart;

    private AdresseArrivee adresseArrivee;

    private Integer nbPlaceDispo;

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

    public Integer getNbPlaceDispo() {
        return nbPlaceDispo;
    }

    public void setNbPlaceDispo(Integer nbPlaceDispo) {
        this.nbPlaceDispo = nbPlaceDispo;
    }
}
