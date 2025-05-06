package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PreguntaRellenarHuecosTest {

	@Test
	public void testRespuestaCorrectaExacta() {
        PreguntaRellenarHuecos pregunta = new PreguntaRellenarHuecos("Completa: ","The sun ___ in the east", "rises");
        assertTrue(pregunta.isCorrecta("rises"));
    }

    @Test
    public void testRespuestaIncorrecta() {
        PreguntaRellenarHuecos pregunta = new PreguntaRellenarHuecos("Completa: ","The moon ___ at night", "shines");
        assertFalse(pregunta.isCorrecta("glows"));
        assertFalse(pregunta.isCorrecta(""));
        assertFalse(pregunta.isCorrecta(null));
    }
}
