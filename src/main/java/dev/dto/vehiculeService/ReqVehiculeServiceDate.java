package dev.dto.vehiculeService;

import java.time.LocalDate;

public class ReqVehiculeServiceDate {
	private LocalDate date;

	public ReqVehiculeServiceDate(String date) {
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
