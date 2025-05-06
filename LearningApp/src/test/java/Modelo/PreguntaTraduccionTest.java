package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PreguntaTraduccionTest {

	@Test
	void testRespuestaCorrectaCoincide() {
		
		PreguntaTraduccion pregunta = new PreguntaTraduccion("¿Cómo se dice 'perro' en inglés?", "dog");
		assertTrue(pregunta.isCorrecta("DOG"));
		assertTrue(pregunta.isCorrecta("Dog"));
		assertTrue(pregunta.isCorrecta("dog"));
	}
	
	@Test
	void testRespuestaIncorrecta() {
		PreguntaTraduccion pregunta = new PreguntaTraduccion("Traduce: Dame dos, por favor", "Please, give me two");
		assertFalse(pregunta.isCorrecta("Give me two, please"));
		assertFalse(pregunta.isCorrecta(""));
		assertFalse(pregunta.isCorrecta(null));
	}
	
}
