package dev.dto.reservation.vehicule;

import java.time.LocalDateTime;

/**
 * DTO Réservation véhicule (création de la résa)
 */
public class ReservationVehiculeDto {

    private Integer vehicule;

    private Integer chauffeur;

    private LocalDateTime dateHeureDepart;

    private LocalDateTime dateHeureRetour;

    private Integer passager;

    private Boolean demandeChauffeur;

    public Integer getVehicule() {
        return vehicule;
    }

    public void setVehicule(Integer vehicule) {
        this.vehicule = vehicule;
    }

    public Integer getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Integer chauffeur) {
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

    public Integer getPassager() {
        return passager;
    }

    public void setPassager(Integer passager) {
        this.passager = passager;
    }

    public Boolean getDemandeChauffeur() {
        return demandeChauffeur;
    }

    public void setDemandeChauffeur(Boolean demandeChauffeur) {
        this.demandeChauffeur = demandeChauffeur;
    }
}
