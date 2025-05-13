package Controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Modelo.BloqueContenido;
import Modelo.Curso;
import Modelo.Pregunta;
import Modelo.PreguntaTraduccion;

class ControladorTestUnitarias {

	@Test
	void testCursosEnOrdenLikes() {
		
		BloqueContenido b1 = new BloqueContenido();
		BloqueContenido b2 = new BloqueContenido();
		BloqueContenido b3 = new BloqueContenido();
		List<BloqueContenido> bl = new LinkedList<BloqueContenido>();
		bl.add(b1);
		bl.add(b2);
		bl.add(b3);
		Curso c1 = new Curso("c1", "c1", bl, 2, 3, "Español");
		Curso c2 = new Curso("c2", "c2", bl, 2, 4, "Español");
		Curso c3 = new Curso("c2", "c2", bl, 2, 6, "Español");	
		List<Curso> cursosOnline = new LinkedList<Curso>();
		cursosOnline.add(c1);
		cursosOnline.add(c2);
		cursosOnline.add(c3);
		
		List<Curso> ordenLikes = Controlador.INSTANCE.getCursosEnOrdenLikes(cursosOnline);
		assertTrue(c3.equals(ordenLikes.get(0)));
		assertTrue(c2.equals(ordenLikes.get(1)));
		assertTrue(c1.equals(ordenLikes.get(2)));
	}
	
	@Test
	void testCursosEnOrdenDescargas() {
		
		BloqueContenido b1 = new BloqueContenido();
		BloqueContenido b2 = new BloqueContenido();
		BloqueContenido b3 = new BloqueContenido();
		List<BloqueContenido> bl = new LinkedList<BloqueContenido>();
		bl.add(b1);
		bl.add(b2);
		bl.add(b3);
		Curso c1 = new Curso("c1", "c1", bl, 4, 3, "Español");
		Curso c2 = new Curso("c2", "c2", bl, 5, 4, "Español");
		Curso c3 = new Curso("c2", "c2", bl, 2, 6, "Español");	
		List<Curso> cursosOnline = new LinkedList<Curso>();
		cursosOnline.add(c1);
		cursosOnline.add(c2);
		cursosOnline.add(c3);
		
		List<Curso> ordenDescargas = Controlador.INSTANCE.getCursosEnOrdenDescargas(cursosOnline);
		assertTrue(c2.equals(ordenDescargas.get(0)));
		assertTrue(c1.equals(ordenDescargas.get(1)));
		assertTrue(c3.equals(ordenDescargas.get(2)));
	}
	
	@Test
	void obtenerPreguntasAleatoria() {
	    Pregunta p1 = new PreguntaTraduccion("hola", "hello");
	    Pregunta p2 = new PreguntaTraduccion("one", "uno");
	    Pregunta p3 = new PreguntaTraduccion("two", "dos");
	    BloqueContenido bloque = new BloqueContenido("Bloque 1", Arrays.asList(p1, p2, p3));
	    Curso curso = new Curso("c1", "c1", Arrays.asList(bloque));
	    List<Pregunta> aleatorias = Controlador.INSTANCE.obtenerPreguntasAleatoria(curso);
	    
	    assertNotNull(aleatorias);
	    assertEquals(3, aleatorias.size());
	    assertTrue(aleatorias.containsAll(List.of(p1, p2, p3)));
	       
	    }

	    @Test
	    void obtenerPreguntasRepeticionEspaciada() {
		    Pregunta p1 = new PreguntaTraduccion("hola", "hello");
		    Pregunta p2 = new PreguntaTraduccion("one", "uno");
		    Pregunta p3 = new PreguntaTraduccion("two", "dos");
		    BloqueContenido bloque = new BloqueContenido("Bloque 1", Arrays.asList(p1, p2, p3));
		    Curso curso = new Curso("c1", "c1", Arrays.asList(bloque));
		    List<Pregunta> resultado = Controlador.INSTANCE.obtenerPreguntasRepeticionEspaciada(curso);
		    
		    assertNotNull(resultado);
		    assertTrue(resultado.size() >= 3);
		    assertTrue(resultado.containsAll(List.of(p1, p2, p3)));
	    }

	    @Test
	    void obtenerPreguntasSecuencial() {
	    	Pregunta p1 = new PreguntaTraduccion("hola", "hello");
		    Pregunta p2 = new PreguntaTraduccion("one", "uno");
		    Pregunta p3 = new PreguntaTraduccion("two", "dos");
		    BloqueContenido bloque = new BloqueContenido("Bloque 1", Arrays.asList(p1, p2, p3));
		    Curso curso = new Curso("c1", "c1", Arrays.asList(bloque));
		    List<Pregunta> secuencial = Controlador.INSTANCE.obtenerPreguntasSecuencial(curso);
		    
		    assertNotNull(secuencial);
		    assertEquals(3, secuencial.size());
		   	assertEquals(List.of(p1, p2, p3), secuencial);	       
	    }
}
