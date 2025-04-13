
package Controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Modelo.BloqueContenido;
import Modelo.Curso;


class ControladorTest {

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
	

}
