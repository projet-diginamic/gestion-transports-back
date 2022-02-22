package dev.execTest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import dev.exception.entites.VehiculeService;
import dev.repositories.VehiculeServiceRepository;

//@Controller
public class TestVehiculeService implements CommandLineRunner {

	private VehiculeServiceRepository vsr;

	public TestVehiculeService(VehiculeServiceRepository vsr) {
		super();
		this.vsr = vsr;
	}

	@Override
	public void run(String... args) throws Exception {
		List<VehiculeService> vs = vsr.findAll();

		System.out.println("*****Véhicules service*****");
		for (VehiculeService vs1 : vs) {
			System.out.println(vs1.getMarque());
		}

	}

}
