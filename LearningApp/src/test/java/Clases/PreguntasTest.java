package Clases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PreguntasTest {

	@Test
	void testisCorrectaPreguntasRellenarHuecos() {
		PreguntaRellenarHuecos p1 = new PreguntaRellenarHuecos("Rellena", "I --- pizza", "like");
		assertTrue(p1.isCorrecta("like"));
		assertTrue(p1.isCorrecta("LIkE"));
		assertFalse(p1.isCorrecta("do"));
	}
	
	@Test
	void testisCorrectaPreguntasTest() {
		List<String> opciones = new LinkedList<String>(List.of("play", "do", "practice"));
		PreguntaTest p1 = new PreguntaTest("I -- judo", opciones, "do");
		assertFalse(p1.isCorrecta("like"));
		assertFalse(p1.isCorrecta("LIkE"));
		assertTrue(p1.isCorrecta("do"));
		assertTrue(p1.isCorrecta("DO"));
	}
	
	@Test
	void testisCorrectaPreguntaTraducir() {
		PreguntaTraduccion p1 = new PreguntaTraduccion("The cat is naughty ", "El gato es travieso");
		assertFalse(p1.isCorrecta("like"));
		assertTrue(p1.isCorrecta("El gato es travieso"));
		
	}

}
