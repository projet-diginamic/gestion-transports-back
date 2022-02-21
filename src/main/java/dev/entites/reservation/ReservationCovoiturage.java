package dev.entites.reservation;

import dev.entites.AnnonceCovoiturage;

import javax.persistence.*;

@Entity
public class ReservationCovoiturage extends Reservation {

    @ManyToOne
    @JoinColumn(name="id_covoiturage")
    private AnnonceCovoiturage annonceCovoiturage;

    public AnnonceCovoiturage getAnnonceCovoiturage() {
        return annonceCovoiturage;
    }

    public void setAnnonceCovoiturage(AnnonceCovoiturage annonceCovoiturage) {
        this.annonceCovoiturage = annonceCovoiturage;
    }

}
