package dev.dto.vehiculeService;

//{
//    "immatriculation": "BH-152-HG",
//    "marque": "Jaguar",
//    "modele": "Jag",
//    "nbPlaces": 4,
//    "photo": "photo",
//    "categorie": 1
//}

public class CreerVehiculeServiceDto {

	private String immatriculation;
	private String marque;
	private String modele;
	private Integer nbPlaces;
	private String photo;
	private Integer categorie;

	public CreerVehiculeServiceDto(String immatriculation, String marque, String modele, Integer nbPlaces, String photo,
			Integer categorie) {
		super();
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nbPlaces = nbPlaces;
		this.photo = photo;
		this.categorie = categorie;
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

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getCategorie() {
		return categorie;
	}

	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}

}
