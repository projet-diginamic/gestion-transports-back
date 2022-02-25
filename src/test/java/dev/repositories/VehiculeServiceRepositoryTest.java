package dev.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.dto.vehiculeService.VehiculeServiceListeDto;

@SpringBootTest
public class VehiculeServiceRepositoryTest {
	@Autowired
	VehiculeServiceRepository vSRepo;

	@Test
	void testRechercherParMarque() {
		// DATA => (1, 'PK-156-ML', 'Toyota', 'Yaris', 5, 'En service',
		// 'https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif',
		// 1),

		Iterable<VehiculeServiceListeDto> vehiculeService = vSRepo.rechercherParMarque("Toyota");
		if (!vehiculeService.iterator().hasNext())
			assertThat(false).isTrue();
		VehiculeServiceListeDto v = vehiculeService.iterator().next();

		assertThat(v.getImmatriculation()).isEqualTo("PK-156-ML");
		assertThat(v.getModele()).isEqualTo("Yaris");
	}

	@Test
	void testRechercherParImmatriculation() {
		// DATA => (1, 'PK-156-ML', 'Toyota', 'Yaris', 5, 'En service',
		// 'https://img-31.ccm2.net/oUzoumT47RAmihtpA_Gbxo4mAYA=/1240x/smart/dc46cec6193b4acd8570ebfa77adc2ce/ccmcms-hugo/10557729.gif',
		// 1),

		Iterable<VehiculeServiceListeDto> vehiculeService = vSRepo.rechercherParImmatriculation("PK-156-ML");
		if (!vehiculeService.iterator().hasNext())
			assertThat(false).isTrue();
		VehiculeServiceListeDto v = vehiculeService.iterator().next();

		assertThat(v.getModele()).isEqualTo("Yaris");
		assertThat(v.getMarque()).isEqualTo("Toyota");
	}
}
