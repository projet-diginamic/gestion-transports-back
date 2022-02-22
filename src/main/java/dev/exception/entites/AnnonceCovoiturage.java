package dev.exception.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.exception.entites.adresse.AdresseArrivee;
import dev.exception.entites.adresse.AdresseDepart;
import dev.exception.entites.reservation.ReservationCovoiturage;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AnnonceCovoiturage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateHeureDepart;

    @ManyToOne
    @JoinColumn(name="id_organisateur")
    private Utilisateur organisateur;

    @OneToOne
    @JoinColumn(name="adresse_depart")
    private AdresseDepart adresseDepart;

    @OneToOne
    @JoinColumn(name="adresse_arrivee")
    private AdresseArrivee adresseArrivee;

    @ManyToOne
    @JoinColumn(name="id_vehicule")
    private VehiculeCovoiturage vehicule;

    @OneToMany(mappedBy = "annonceCovoiturage")
    @JsonIgnore
    private List<ReservationCovoiturage> resas;

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

    public Utilisateur getOrganisateur() {
        return organisateur;
    }

    public void setNbPlaces(Integer nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public void setOrganisateur(Utilisateur organisateur) {
        this.organisateur = organisateur;
    }

    public VehiculeCovoiturage getVehicule() {
        return vehicule;
    }

    public void setVehicule(VehiculeCovoiturage vehicule) {
        this.vehicule = vehicule;
    }

    public List<ReservationCovoiturage> getResas() {
        return resas;
    }

    public void setResas(List<ReservationCovoiturage> resas) {
        this.resas = resas;
    }
}
