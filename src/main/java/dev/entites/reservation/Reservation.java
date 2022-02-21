package dev.entites.reservation;

import dev.entites.Utilisateur;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_collaborateur")
    private Utilisateur passager;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Utilisateur getPassager() {
        return passager;
    }

    public void setPassager(Utilisateur passager) {
        this.passager = passager;
    }
}
