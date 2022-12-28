package com.project.progettorisikorisikiamobackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProgettoRisikoRisikiamoBackendApplicationTests {

	@Test
	void contextLoads() {
		// Test per conferma coverege
		assert true;
	}
	@Test // Test dado
	void testDice() {
		// Test per conferma coverege
		Dice d = new Dice(6);
		assert d.getSides() == 6;
		assert d.getValue() == 0;
		d.roll();
		assert d.getValue() > 0;
		assert d.getValue() <= 6;
		d.setValue(5);
		assert d.getValue() == 5;
		d.setSides(10);
		assert d.getSides() == 10;
	}

}
