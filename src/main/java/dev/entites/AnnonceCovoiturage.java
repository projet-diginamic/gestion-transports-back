package dev.entites;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnnonceCovoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateHeureDepart;

    @OneToOne
    @JoinColumn(name="adresse_depart")
    private AdresseDepart adresseDepart;

    @OneToOne
    @JoinColumn(name="adresse_arrivee")
    private AdresseArrivee adresseArrivee;

    private Integer nbPlaces;

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

    public Integer getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaceDispo(Integer nbPlaceDispo) {
        this.nbPlaces = nbPlaceDispo;
    }
}
