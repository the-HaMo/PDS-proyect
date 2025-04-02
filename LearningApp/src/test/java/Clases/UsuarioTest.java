package Clases;

import org.junit.jupiter.api.*;
import jakarta.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
    private EntityTransaction tx;
    
    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("ejemplo");
    }
	
    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    
    @AfterEach
    public void tearDown() {
        if (em != null) em.close();
    }
    
    @AfterAll
    public static void close() {
        if (emf != null) emf.close();
    }
    
    @Test
    public void testPersistenciaEstudiante() {
    
    	Estudiante estudiante = new Estudiante("Laura", "1234");
    	
    	tx.begin();
    	em.persist(estudiante);
    	tx.commit();
    	
    	Usuario encontrado = em.find(Estudiante.class, estudiante.getId());
    	assertNotNull(encontrado);
    	assertEquals("Laura", encontrado.getNombre());
    	assertTrue(encontrado instanceof Estudiante);
    	
    }
    
    @Test
    public void testPersistenciaColaborador() {
        Colaborador colaborador = new Colaborador("Carlos", "adminpass");

        tx.begin();
        em.persist(colaborador);
        tx.commit();

        Usuario encontrado = em.find(Colaborador.class, colaborador.getId());
        assertNotNull(encontrado);
        assertEquals("Carlos", encontrado.getNombre());
        assertTrue(encontrado instanceof Colaborador);
    }
    
    
}
