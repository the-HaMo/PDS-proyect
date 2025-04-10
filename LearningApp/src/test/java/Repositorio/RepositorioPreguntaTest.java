package Repositorio;

import Modelo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioPreguntaTest {

    private static RepositorioPregunta repositorio;
    private static BloqueContenido bloqueDummy;

    private static Integer idTest, idTraduccion, idHuecos;

    @BeforeAll
    public static void setUp() {
        repositorio = new RepositorioPregunta();
        bloqueDummy = crearBloqueContenidoEnDB(); // Bloque válido para relacionar preguntas
        
    }

    private static BloqueContenido crearBloqueContenidoEnDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
        EntityManager em = emf.createEntityManager();

        BloqueContenido bloque = new BloqueContenido("Bloque de prueba");

        em.getTransaction().begin();
        em.persist(bloque);
        em.getTransaction().commit();
        em.close();
        return bloque;
    }

    @Test
    @Order(1)
    public void testGuardarPreguntas() {
        PreguntaTest p1 = new PreguntaTest(
                "¿Cómo se dice 'amigo' en francés?",
                Arrays.asList("Ami", "Amour", "Friend"),
                "Ami"
        );
        p1.setBloqueContenido(bloqueDummy);
        repositorio.guardarPregunta(p1);
        idTest = p1.getId();

        PreguntaTraduccion p2 = new PreguntaTraduccion(
                "Traduce 'dog' al español",
                "perro"
        );
        p2.setBloqueContenido(bloqueDummy);
        repositorio.guardarPregunta(p2);
        idTraduccion = p2.getId();

        PreguntaRellenarHuecos p3 = new PreguntaRellenarHuecos(
                "Completa: Water ___ at 100 degrees.",
                "Water ___ at 100 degrees.",
                "boils"
        );
        p3.setBloqueContenido(bloqueDummy);
        repositorio.guardarPregunta(p3);
        idHuecos = p3.getId();

        assertNotNull(idTest);
        assertNotNull(idTraduccion);
        assertNotNull(idHuecos);
    }

    @Test
    @Order(2)
    public void testObtenerPorIdYTipo() {
        Pregunta p1 = repositorio.obtenerPreguntaPorId(idTest);
        assertTrue(p1 instanceof PreguntaTest);

        Pregunta p2 = repositorio.obtenerPreguntaPorId(idTraduccion);
        assertTrue(p2 instanceof PreguntaTraduccion);

        Pregunta p3 = repositorio.obtenerPreguntaPorId(idHuecos);
        assertTrue(p3 instanceof PreguntaRellenarHuecos);
    }

    @Test
    @Order(3)
    public void testObtenerPorTipo() {
        List<PreguntaTest> tests = repositorio.obtenerPreguntasTest();
        assertFalse(tests.isEmpty());

        List<PreguntaTraduccion> traducciones = repositorio.obtenerPreguntasTraduccion();
        assertFalse(traducciones.isEmpty());

        List<PreguntaRellenarHuecos> huecos = repositorio.obtenerPreguntasRellenarHuecos();
        assertFalse(huecos.isEmpty());
    }

    @Test
    @Order(4)
    public void testEliminarPregunta() {
        repositorio.eliminarPreguntaPorId(idHuecos);
        Pregunta eliminada = repositorio.obtenerPreguntaPorId(idHuecos);
        assertNull(eliminada);
    }
}
