package dev.dto.vehiculeService;

import java.time.LocalDate;

public class ReqVehicule {
	private LocalDate date;

	public ReqVehicule(String date) {
		super();
		if (date != null)
			this.date = LocalDate.parse(date);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate dateHeureDepart) {
		this.date = dateHeureDepart;
	}
}
