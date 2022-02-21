package dev.dto.vehiculeService;

//{

//immatriculation
//marque
//modele
//libelleCategorieVehicule
//nbPlaces
//statutVehicule
//photoURL
//}

public class ModifierVehiculeServiceDto {

	private String immatriculation;
	private String marque;
	private String modele;
	private Integer categorie;
	private Integer nbPlaces;
	private String statutVehicule;
	private String photoURL;

	public ModifierVehiculeServiceDto(String immatriculation, String marque, String modele, Integer categorie,
			Integer nbPlaces, String statutVehicule, String photoURL) {
		super();
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.categorie = categorie;
		this.nbPlaces = nbPlaces;
		this.statutVehicule = statutVehicule;
		this.photoURL = photoURL;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Integer getCategorie() {
		return categorie;
	}

	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getStatutVehicule() {
		return statutVehicule;
	}

	public void setStatutVehicule(String statutVehicule) {
		this.statutVehicule = statutVehicule;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

}
