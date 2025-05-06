package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PreguntaTestTest {

	@Test
    void testRespuestaCorrecta() {
        PreguntaTest pregunta = new PreguntaTest("¿Cuál es la capital de Francia?", Arrays.asList("Madrid", "París", "Roma"), "París");
        assertTrue(pregunta.isCorrecta("París"));
        assertTrue(pregunta.isCorrecta("parís")); //Mayusculas
        assertTrue(pregunta.isCorrecta("  París  ")); //Espacios
    }

    @Test
    void testRespuestaIncorrecta() {
        PreguntaTest pregunta = new PreguntaTest("¿Cuál es la capital de Francia?", Arrays.asList("Madrid", "París", "Roma"), "París");
        assertFalse(pregunta.isCorrecta("Roma"));
        assertFalse(pregunta.isCorrecta(""));
        assertFalse(pregunta.isCorrecta(null));
    }

}
