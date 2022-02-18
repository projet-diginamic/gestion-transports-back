package dev.entites;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class AdresseDepart {

	@Embedded
	private Adresse adresse;

}
