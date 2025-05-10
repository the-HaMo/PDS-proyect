package Repositorio;

import Modelo.*;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepositorioProgresoCursoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;

    private Colaborador colaborador;
    private Estudiante estudiante; 
    private Curso curso;
    private BloqueContenido bloque;
    private ProgresoCurso progreso;

    @BeforeAll
    public static void setupOnce() {
        emf = Persistence.createEntityManagerFactory("ejemplo");
    }

    @BeforeEach
    public void setup() {
    	em = emf.createEntityManager();

    	// PRIMERA TRANSACCIÓN: limpiar base de datos
    	em.getTransaction().begin();
    	em.createQuery("DELETE FROM ProgresoCurso").executeUpdate();
    	em.createQuery("DELETE FROM EstadisticaUsuario").executeUpdate();
    	em.createQuery("DELETE FROM Usuario").executeUpdate();
    	em.createQuery("DELETE FROM Curso").executeUpdate();
    	em.createQuery("DELETE FROM BloqueContenido").executeUpdate();
    	em.getTransaction().commit();

    	// SEGUNDA TRANSACCIÓN: insertar datos de prueba
    	em.getTransaction().begin();
    	colaborador = new Colaborador("pepe", "1234");
    	estudiante = new Estudiante("anita", "ana_2312");
    	curso = new Curso("Curso de prueba", "Descripción");
    	curso.setAutor(colaborador);
    	bloque = new BloqueContenido("Bloque de prueba");

    	curso.getBloquesContenidos().add(bloque);
    	colaborador.addCurso(curso);

    	em.persist(colaborador);
    	em.persist(estudiante);
    	em.persist(curso);
    	em.persist(bloque);

    	progreso = new ProgresoCurso(estudiante, curso, bloque);
    	em.persist(progreso);
    	em.getTransaction().commit();

    }

    @AfterEach
    public void cleanup() {
        if (em.isOpen()) {
            em.close();
        }
    }


    @AfterAll
    public static void tearDownOnce() {
        emf.close();
    }

    @Test
    @Order(1)
    public void testGuardarYBuscarProgreso() {
        List<ProgresoCurso> lista = RepositorioProgresoCurso.buscarPorEstudianteYCurso(estudiante, curso);
        assertEquals(1, lista.size());
        assertEquals(bloque.getId(), lista.get(0).getBloque().getId());
    }

    @Test
    @Order(2)
    public void testEstaCompletadoTrue() {
        boolean completado = RepositorioProgresoCurso.estaCompletado(estudiante, curso, bloque);
        assertTrue(completado);
    }

    @Test
    @Order(3)
    public void testEstaCompletadoFalse() {
        BloqueContenido otroBloque = new BloqueContenido("Otro Bloque");
        em.getTransaction().begin();
        em.persist(otroBloque);
        em.getTransaction().commit();

        boolean completado = RepositorioProgresoCurso.estaCompletado(estudiante, curso, otroBloque);
        assertFalse(completado);

        em.getTransaction().begin();
        em.remove(em.merge(otroBloque));
        em.getTransaction().commit();
    }
}
