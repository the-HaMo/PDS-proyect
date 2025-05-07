package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PreguntasTest {

	@Test
	public void testRespuestaCorrectaExactaHuecos() {
        PreguntaRellenarHuecos pregunta = new PreguntaRellenarHuecos("Completa: ","The sun ___ in the east", "rises");
        assertTrue(pregunta.isCorrecta("rises"));
    }

    @Test
    public void testRespuestaIncorrectaHuecos() {
        PreguntaRellenarHuecos pregunta = new PreguntaRellenarHuecos("Completa: ","The moon ___ at night", "shines");
        assertFalse(pregunta.isCorrecta("glows"));
        assertFalse(pregunta.isCorrecta(""));
        assertFalse(pregunta.isCorrecta(null));
    }
    
	@Test
	void testRespuestaCorrectaCoincideTraduccion() {
		
		PreguntaTraduccion pregunta = new PreguntaTraduccion("¿Cómo se dice 'perro' en inglés?", "dog");
		assertTrue(pregunta.isCorrecta("DOG"));
		assertTrue(pregunta.isCorrecta("Dog"));
		assertTrue(pregunta.isCorrecta("dog"));
	}
	
	@Test
	void testRespuestaIncorrectaTraduccion() {
		PreguntaTraduccion pregunta = new PreguntaTraduccion("Traduce: Dame dos, por favor", "Please, give me two");
		assertFalse(pregunta.isCorrecta("Give me two, please"));
		assertFalse(pregunta.isCorrecta(""));
		assertFalse(pregunta.isCorrecta(null));
	}
	
	@Test
    void testRespuestaCorrectaTest() {
        PreguntaTest pregunta = new PreguntaTest("¿Cuál es la capital de Francia?", Arrays.asList("Madrid", "París", "Roma"), "París");
        assertTrue(pregunta.isCorrecta("París"));
        assertTrue(pregunta.isCorrecta("parís")); //Mayusculas
        assertTrue(pregunta.isCorrecta("  París  ")); //Espacios
    }

    @Test
    void testRespuestaIncorrectaTest() {
        PreguntaTest pregunta = new PreguntaTest("¿Cuál es la capital de Francia?", Arrays.asList("Madrid", "París", "Roma"), "París");
        assertFalse(pregunta.isCorrecta("Roma"));
        assertFalse(pregunta.isCorrecta(""));
        assertFalse(pregunta.isCorrecta(null));
    }

}
