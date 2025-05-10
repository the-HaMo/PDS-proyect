package Modelo;

import static org.junit.jupiter.api.Assertions.*;
import Modelo.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ColaboradorTest {

	
	private Colaborador colaborador;
    private Curso curso1;
    private Curso curso2;
	
    @BeforeEach
    public void setUp() {
        colaborador = new Colaborador("Pepe", "1234");
        curso1 = new Curso("Curso A","Curso para mejorar nivel de inglés");
        curso2 = new Curso("Curso B","Curso para mejorar nivel de inglés");
    }
	
    @Test
    public void testAddCursoUnaVez() {
    	colaborador.addCurso(curso1);
        List<Curso> cursos = colaborador.getCursos();

        assertEquals(1, cursos.size());
        assertTrue(cursos.contains(curso1));
    }
    
    
    @Test
    public void testAddCursoDuplicadoNoDuplica() {
    	colaborador.addCurso(curso2);
    	colaborador.addCurso(curso2);
    	List<Curso> cursos = colaborador.getCursos();
    	assertEquals(1, cursos.size());
    }
    

}
