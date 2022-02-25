package dev.services;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import dev.dto.vehiculeService.VehiculeServiceListeDto;
import dev.exception.ListeVideException;

@SpringBootTest
public class VehiculeServiceServiceTest {

	@Autowired
	VehiculeServiceService vehiculeServiceService;

	@Test
	void afficherVehiculeServiceTest() throws ListeVideException {

		List<VehiculeServiceListeDto> resultat = vehiculeServiceService.afficherVehiculesService(PageRequest.of(0, 2));
		Assertions.assertThat(resultat).isNotEmpty();
		Assertions.assertThat(resultat).hasSize(2);
	}
}
