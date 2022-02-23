package dev.dto.vehiculeService;

/*
{
        "id": 1,
        "marque": "Toyota",
        "modele": "Yaris",
        "immatriculation": "PK-156-ML",
        "photo": "https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif"
}
 */

public class VehiculeServiceListeDtoCollaborateur {

	private Integer id;
	private String marque;
	private String modele;
	private String immatriculation;
	private String photo;

	public VehiculeServiceListeDtoCollaborateur(Integer id, String marque, String modele, String immatriculation,
			String photo) {
		super();
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.photo = photo;
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

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getId() {
		return id;
	}

}
