package dev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DemoTest {
	@Test
	void uneMethodeDeTest1() {
		System.out.println("je suis une méthode de test 1 ");
	}

	@Test
	void uneMethodeDeTest2() {
		System.out.println("je suis une méthode de test 2 ");
	}

	@BeforeEach
	void uneMethodeDeTestAvant() {
		System.out.println("je suis une méthode de test avant ");
	}
}
