package dev.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.entites.reservation.ReservationVehicule;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TauxOccupation implements Comparable<TauxOccupation>{

    private LocalDate date;

    private Double taux;

    @JsonIgnore
    private final Integer TEMPS_TRAVAIL = 360;

    public TauxOccupation(ReservationVehicule resa) {
        this.date = resa.getDateHeureDepart().toLocalDate();
        this.taux = (double) ChronoUnit.MINUTES.between(resa.getDateHeureDepart(), resa.getDateHeureRetour()) / TEMPS_TRAVAIL;
        System.out.println(date+" : "+taux);
    }

    public TauxOccupation(LocalDate d){
        this.date = d;
        this.taux = 0D;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTaux() {
        return taux;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }


    @Override
    public int compareTo(TauxOccupation o) {
        return this.date.compareTo(o.getDate());
    }
}
