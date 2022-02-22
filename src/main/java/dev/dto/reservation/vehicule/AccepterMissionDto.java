package dev.dto.reservation.vehicule;

/**
 * Classe pour les gérer l'attribution des réservations véhicules de service avec chauffeur
 */
public class AccepterMissionDto {

    private Integer chauffeur;

    private Integer resa;

    public Integer getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Integer chauffeur) {
        this.chauffeur = chauffeur;
    }

    public Integer getResa() {
        return resa;
    }

    public void setResa(Integer resa) {
        this.resa = resa;
    }
}
