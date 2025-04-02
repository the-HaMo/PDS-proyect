package Clases;

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
		List<BloqueContenido> bl = new LinkedList<BloqueContenido>();
		bl.add(b1);
		bl.add(b2);
		bl.add(b3);
		Curso c1 = new Curso("c1", "c1", bl, 2, 3);
		assertTrue(c1.getBloquesContenidos().size() == 3);
	}
	
	
}
