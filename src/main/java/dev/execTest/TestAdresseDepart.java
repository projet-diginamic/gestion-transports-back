package dev.execTest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import dev.entites.adresse.AdresseDepart;
import dev.repositories.AdresseDepartRepository;

@Controller
public class TestAdresseDepart implements CommandLineRunner {

	private AdresseDepartRepository adr;

	public TestAdresseDepart(AdresseDepartRepository adr) {
		super();
		this.adr = adr;
	}

	@Override
	public void run(String... args) throws Exception {
		List<AdresseDepart> ad = adr.findAll();

		System.out.println("*****Adresses départ*****");
		for (AdresseDepart ad1 : ad) {
			System.out.println(ad1.getAdresse().getRue());
		}

	}

}
