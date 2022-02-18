package dev.entites.reservation;

import dev.entites.AnnonceCovoiturage;

import javax.persistence.*;

@Entity
public class ReservationCovoiturage extends Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private AnnonceCovoiturage annonceCovoiturage;

    public AnnonceCovoiturage getAnnonceCovoiturage() {
        return annonceCovoiturage;
    }

    public void setAnnonceCovoiturage(AnnonceCovoiturage annonceCovoiturage) {
        this.annonceCovoiturage = annonceCovoiturage;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
