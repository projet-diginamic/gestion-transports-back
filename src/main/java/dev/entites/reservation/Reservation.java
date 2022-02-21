package dev.entites.reservation;

import dev.entites.Collaborateur;
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
    private Collaborateur passager;

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
}
