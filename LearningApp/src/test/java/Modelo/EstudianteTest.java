package Modelo;

import static org.junit.jupiter.api.Assertions.*;
import Modelo.*;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstudianteTest {

	
	private Estudiante estudiante;
    private Curso curso1;
    private Curso curso2;
	
    @BeforeEach
    public void setUp() {
        estudiante = new Estudiante("Pepe", "1234");
        curso1 = new Curso("Curso A","Curso para mejorar nivel de inglés");
        curso2 = new Curso("Curso B","Curso para mejorar nivel de inglés");
    }
	
    @Test
    public void testAddCursoUnaVez() {
        estudiante.addCurso(curso1);
        List<Curso> cursos = estudiante.getCursos();

        assertEquals(1, cursos.size());
        assertTrue(cursos.contains(curso1));
    }
    
    
    @Test
    public void testAddCursoDuplicadoNoDuplica() {
    	estudiante.addCurso(curso2);
    	estudiante.addCurso(curso2);
    	List<Curso> cursos = estudiante.getCursos();
    	assertEquals(1, cursos.size());
    }//Bien ahí
    
    
    @Test
    public void testAddCursoEmpezado() {
    	estudiante.addCursoEmpezado(curso1, Estrategia.SECUENCIAL);
    	Map<Curso, Estrategia> empezados = estudiante.getCursosEmpezados();
    	
    	assertEquals(1, empezados.size());
    	assertEquals(empezados.get(curso1), Estrategia.SECUENCIAL);
    }
    
}
