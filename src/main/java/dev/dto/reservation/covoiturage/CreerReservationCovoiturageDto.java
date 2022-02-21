package dev.dto.reservation.covoiturage;

public class CreerReservationCovoiturageDto {

    private Integer idCollaborateur;
    private Integer idCovoiturage;

    public Integer getIdCollaborateur() {
        return idCollaborateur;
    }

    public void setIdCollaborateur(Integer idCollaborateur) {
        this.idCollaborateur = idCollaborateur;
    }

    public Integer getIdCovoiturage() {
        return idCovoiturage;
    }

    public void setIdCovoiturage(Integer idCovoiturage) {
        this.idCovoiturage = idCovoiturage;
    }
}
