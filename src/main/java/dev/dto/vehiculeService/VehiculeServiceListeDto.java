package dev.dto.vehiculeService;

/*
{
         "id": 1,
        "immatriculation": "PK-156-ML",
        "marque": "Toyota",
        "modele": "Yaris",
        "statut": "En service",
        "categorie": "Citadines polyvalentes",
        "photo": "https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif"
}
 */

public class VehiculeServiceListeDto {

	private Integer id;
	private String immatriculation;
	private String marque;
	private String modele;
	private String categorie;
	private String photo;

	public VehiculeServiceListeDto(Integer id, String immatriculation, String marque, String modele, String categorie,
			String photo) {
		super();
		this.id = id;
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.categorie = categorie;
		this.photo = photo;
	}

	public Integer getId() {
		return id;
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

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
