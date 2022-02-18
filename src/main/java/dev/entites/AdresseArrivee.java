package dev.entites;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class AdresseArrivee {

	@Embedded
	private Adresse adresse;

}
