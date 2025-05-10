package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


class LikeTest {

	
	private Usuario usuario;
	private Curso curso;

	@BeforeEach
	public void setUp() {
		usuario = new Estudiante("pepe", "1234");
		curso = new Curso("Caso prueba", "Descripción");
	}
	
	@Test
    public void testLikeIgualASiMismo() {
        Like like = new Like(usuario, curso);
        assertEquals(like, like);
    }
	
	@Test
	public void testLikeNoEsIgualAOtro() {
		Usuario usuario2 = new Estudiante("ana", "1234");
		Like like1 = new Like(usuario, curso);
		Like like2 = new Like(usuario2, curso);
		
		assertNotEquals(like1, like2);
	}
	
	@Test
    public void testLikeNoEsIgualANull() {
        Like like = new Like(usuario, curso);
        assertNotEquals(null, like);
    }
	
	@Test
    public void testLikeNoEsIgualAOtroObjeto() {
        Like like = new Like(usuario, curso);
        assertNotEquals("cadena", like);
    }
	
}
