package Clases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.PreguntaRellenarHuecos;
import Modelo.PreguntaTest;
import Modelo.PreguntaTraduccion;

class BloqueContenidoTest {

	@Test
	void testPreguntasBloque() {
		BloqueContenido b1 = new BloqueContenido();
		PreguntaTest p1 = new PreguntaTest();
		PreguntaRellenarHuecos p2 = new PreguntaRellenarHuecos();
		PreguntaTraduccion p3 = new PreguntaTraduccion();
		b1.addPregunta(p1);
		b1.addPregunta(p2);
		b1.addPregunta(p3);
		assertTrue(b1.getPreguntas().size() == 3);
	}
	
	@Test
	void testPreguntasBloqueTotal() {
		BloqueContenido b1 = new BloqueContenido();
		PreguntaTest p1 = new PreguntaTest();
		PreguntaRellenarHuecos p2 = new PreguntaRellenarHuecos();
		PreguntaTraduccion p3 = new PreguntaTraduccion();
		PreguntaRellenarHuecos p4 = new PreguntaRellenarHuecos();
		PreguntaTraduccion p5 = new PreguntaTraduccion();
		b1.addPregunta(p1);
		b1.addPregunta(p2);
		b1.addPregunta(p3);
		b1.addPregunta(p4);
		b1.addPregunta(p5);
		assertTrue(b1.getPreguntas().size() == 5);
	}
	

}
