package dev.dto.vehiculeService;

//id
//"immatriculation":"BZ-123-KL",
//"marque":"tata motors",
//"modele":"pot de yaourt",
//"nbPlaces":0,
//"photo":"url de ma photo",
//"statut
//"categorie":5

public class DetailVehiculeServiceDto {

	private Integer id;
	private String immatriculation;
	private String marque;
	private String modele;
	private Integer nbPlaces;
	private String photo;
	private String statut;
	private Integer categorie;

	public DetailVehiculeServiceDto(Integer id, String immatriculation, String marque, String modele, Integer nbPlaces,
			String photo, String statut, Integer categorie) {
		super();
		this.id = id;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.nbPlaces = nbPlaces;
		this.photo = photo;
		this.statut = statut;
		this.categorie = categorie;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Integer getCategorie() {
		return categorie;
	}

}
