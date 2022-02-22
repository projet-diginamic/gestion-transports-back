package dev.entites.reservation;

import dev.entites.Chauffeur;
import dev.entites.VehiculeService;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


/**
 * Entité Reservation Véhicule
 */
@Entity
public class ReservationVehicule extends Reservation {

    @ManyToOne
    @JoinColumn(name = "id_vehicule")
    private VehiculeService vehicule;

    @ManyToOne
    @JoinColumn(name = "id_chauffeur")
    private Chauffeur chauffeur;

    private LocalDateTime dateHeureDepart;

    private LocalDateTime dateHeureRetour;

    private Boolean demandeChauffeur;

    public VehiculeService getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeService vehicule) {
        this.vehicule = vehicule;
    }


    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public LocalDateTime getDateHeureRetour() {
        return dateHeureRetour;
    }

    public void setDateHeureRetour(LocalDateTime dateHeureRetour) {
        this.dateHeureRetour = dateHeureRetour;
    }

    public Boolean getDemandeChauffeur() {
        return demandeChauffeur;
    }

    public void setDemandeChauffeur(Boolean demandeChauffeur) {
        this.demandeChauffeur = demandeChauffeur;
    }

    public String toString(){
        return "Véhicule de service ----------\n"
                +super.toString()
                +"\nDépart : "+this.dateHeureDepart
                +"\nRetour "+this.dateHeureRetour
                +"\n---------------------\n";
    }
}
