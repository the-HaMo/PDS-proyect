package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CursoTest {

	@Test
	void testBloquesContenidoNumero() {
		BloqueContenido b1 = new BloqueContenido();
		BloqueContenido b2 = new BloqueContenido();
		BloqueContenido b3 = new BloqueContenido();
		Curso c1 = new Curso("c1", "c1");
		c1.addBloque(b1);
		c1.addBloque(b2);
		c1.addBloque(b3);
		assertTrue(c1.getBloquesContenidos().size() == 3);
	}
	
	@Test
	void testLikesyDescargas() {
		Curso c1 = new Curso("c1", "c1");
		c1.AddNumMeGustas();
		c1.AddNumMeGustas();
		c1.AddNumMeGustas();
		c1.addNumDescargas();
		assertTrue(c1.getNumMeGustas() == 3);
		assertTrue(c1.getNumDescargas() == 1);
	}
	
	@Test
	void numPreguntasTotales() {
		PreguntaTest p1 = new PreguntaTest();
		PreguntaTest p2 = new PreguntaTest();
		PreguntaRellenarHuecos p3 = new PreguntaRellenarHuecos();
		PreguntaRellenarHuecos p4 = new PreguntaRellenarHuecos();
		PreguntaTraduccion p5 = new PreguntaTraduccion();
		PreguntaTraduccion p6 = new PreguntaTraduccion();
		BloqueContenido b1 = new BloqueContenido();
		BloqueContenido b2 = new BloqueContenido();
		b1.addPregunta(p1); b1.addPregunta(p2); b1.addPregunta(p3);
		b1.addPregunta(p4); b1.addPregunta(p5); b1.addPregunta(p6);
		b2.addPregunta(p1); b2.addPregunta(p2); b2.addPregunta(p3);
		b2.addPregunta(p4); b2.addPregunta(p5); b2.addPregunta(p6);
		Curso c1 = new Curso();
		c1.addBloque(b1);
		c1.addBloque(b2);
		assertEquals(c1.numPreguntasTotales(), 12);
	}
	
	
	@Test
	void testBloquesContenidoPreguntas() {
		PreguntaTest p1 = new PreguntaTest();
		PreguntaTest p2 = new PreguntaTest();
		PreguntaRellenarHuecos p3 = new PreguntaRellenarHuecos();
		PreguntaRellenarHuecos p4 = new PreguntaRellenarHuecos();
		PreguntaTraduccion p5 = new PreguntaTraduccion();
		PreguntaTraduccion p6 = new PreguntaTraduccion();
		List<Pregunta> preguntas = new LinkedList<Pregunta>();
		preguntas.add(p1); preguntas.add(p2); preguntas.add(p3); 
		preguntas.add(p4); preguntas.add(p5); preguntas.add(p6);
		BloqueContenido b1 = new BloqueContenido("b1", preguntas);
		BloqueContenido b2 = new BloqueContenido("b2", preguntas);
		BloqueContenido b3 = new BloqueContenido("b3", preguntas);
		Curso curso = new Curso("curso", "curso de ejemplo");
		curso.addBloque(b1);
		curso.addBloque(b2);
		curso.addBloque(b3);
		assertTrue(curso.getBloquesContenidos().size() == 3);
	}
}
