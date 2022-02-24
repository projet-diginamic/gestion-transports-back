package dev.entites.reservation;

import dev.entites.Collaborateur;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_collaborateur")
    private Collaborateur passager;

    private String statut;

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collaborateur getPassager() {
        return passager;
    }

    public void setPassager(Collaborateur passager) {
        this.passager = passager;
    }

    public String toString(){
        return "Ref n°"+this.id+"\nPassager : "+this.passager.getPrenom()+" "+this.passager.getNom();
    }
}
