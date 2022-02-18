package dev.execTest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import dev.entites.adresse.AdresseArrivee;
import dev.repositories.AdresseArriveeRepository;

@Controller
public class TestAdresseArrivee implements CommandLineRunner {

	private AdresseArriveeRepository aar;

	public TestAdresseArrivee(AdresseArriveeRepository aar) {
		super();
		this.aar = aar;
	}

	@Override
	public void run(String... args) throws Exception {
		List<AdresseArrivee> aa = aar.findAll();

		System.out.println("*****Adresses d'arrivée*****");
		for (AdresseArrivee aa1 : aa) {
			System.out.println(aa1.getAdresse().getRue());
		}

	}

}
