package dev.exception.entites;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Chauffeur")
public class Chauffeur extends Utilisateur {
	public Chauffeur() {

	}
}
