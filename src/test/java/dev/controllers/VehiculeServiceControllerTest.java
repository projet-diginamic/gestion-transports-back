package dev.controllers;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc // Configuration de l'outil MockMvc
@Transactional
public class VehiculeServiceControllerTest {
	@Autowired // injection de la dépendance MockMvc
	private MockMvc mvc;

	@Test
	void listerVehiculeServiceTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/vehicule-service").param("start", "0").param("size", "2"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].immatriculation").value("PK-156-ML"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].marque").value("Toyota"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].modele").value("Clio"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].categorie").value("Mini-citadine"));
	}
}
