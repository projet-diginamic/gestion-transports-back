package dev.entites.reservation;

import dev.entites.AnnonceCovoiturage;

import javax.persistence.Entity;

@Entity
public class ReservationCovoiturage extends Reservation {

    private AnnonceCovoiturage annonceCovoiturage;

    public AnnonceCovoiturage getAnnonceCovoiturage() {
        return annonceCovoiturage;
    }

    public void setAnnonceCovoiturage(AnnonceCovoiturage annonceCovoiturage) {
        this.annonceCovoiturage = annonceCovoiturage;
    }
}
