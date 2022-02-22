package dev.execTest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import dev.entites.VehiculeCovoiturage;
import dev.repositories.VehiculeCovoiturageRepository;

@Controller
public class TestVehiculeCovoit implements CommandLineRunner {

	private VehiculeCovoiturageRepository vcr;

	public TestVehiculeCovoit(VehiculeCovoiturageRepository vcr) {
		super();
		this.vcr = vcr;
	}

	@Override
	public void run(String... args) throws Exception {
		List<VehiculeCovoiturage> vc = vcr.findAll();

		System.out.println("*****Véhicules covoiturage*****");
		for (VehiculeCovoiturage vc1 : vc) {
			System.out.println(vc1.getImmatriculation());
		}

	}

}
