package Repositorio;

import Modelo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioCursoTest {

    private static RepositorioCurso repositorio;
    private static Integer idCurso;
    private static Usuario creadorDummy;

    @BeforeAll
    public static void setUp() {
        repositorio = new RepositorioCurso();
        creadorDummy = crearUsuarioDummyEnDB(); // Creador válido para asignar al curso
    }

    private static Usuario crearUsuarioDummyEnDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
        EntityManager em = emf.createEntityManager();

        Usuario usuario = new Colaborador();
        usuario.setNombre("Tester");

        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();

        return usuario;
    }

    @Test
    @Order(1)
    public void testGuardarCurso() {
        Curso curso = new Curso();
        curso.setNombre("Curso de Prueba");
        curso.setDescripcion("Este es un curso de prueba.");
        curso.setAutor((Colaborador)creadorDummy);

        repositorio.guardarCurso(curso);
        idCurso = curso.getId();

        assertNotNull(idCurso);
    }

    @Test
    @Order(2)
    public void testObtenerCursoPorId() {
        Curso curso = repositorio.buscarPorId(idCurso);
        assertNotNull(curso);
        assertEquals("Curso de Prueba", curso.getNombre());
        assertEquals("Este es un curso de prueba.", curso.getDescripcion());
        assertEquals(creadorDummy.getId(), curso.getAutor().getId());
    }

    @Test
    @Order(3)
    public void testObtenerTodosLosCursos() {
        List<Curso> cursos = repositorio.obtenerTodos();
        assertFalse(cursos.isEmpty());
    }

    @Test
    @Order(4)
    public void testActualizarCurso() {
        Curso curso = repositorio.buscarPorId(idCurso);
        curso.setDescripcion("Descripción actualizada");

        repositorio.actualizarCurso(curso);

        Curso actualizado = repositorio.buscarPorId(idCurso);
        assertEquals("Descripción actualizada", actualizado.getDescripcion());
    }

    @Test
    @Order(5)
    public void testObtenerCursosPorAutor() {
        List<Curso> cursos = repositorio.obtenerCursosPorAutor(creadorDummy.getId());
        assertFalse(cursos.isEmpty());
        assertEquals(creadorDummy.getId(), cursos.get(0).getAutor().getId());
    }

    @Test
    @Order(6)
    public void testObtenerCursosPorAutorYPublicados() {
        Curso curso = repositorio.buscarPorId(idCurso);
        curso.setEsPublico(true);
        repositorio.actualizarCurso(curso);

        List<Curso> cursosPublicos = repositorio.obtenerCursosPorAutorYPublicados(creadorDummy.getId());
        assertFalse(cursosPublicos.isEmpty());
        assertTrue(cursosPublicos.stream().allMatch(Curso::getEsPublico));
    }

    @Test
    @Order(7)
    public void testObtenerCursosPorAutorPrivados() {
        Curso curso = repositorio.buscarPorId(idCurso);
        curso.setEsPublico(false);
        repositorio.actualizarCurso(curso);

        List<Curso> cursosPrivados = repositorio.obtenerCursosPorAutorPrivados(creadorDummy.getId());
        assertFalse(cursosPrivados.isEmpty());
        assertTrue(cursosPrivados.stream().noneMatch(Curso::getEsPublico));
    }

    @Test
    @Order(8)
    public void testObtenerCursosPublicados() {
        Curso curso = repositorio.buscarPorId(idCurso);
        curso.setEsPublico(true);
        repositorio.actualizarCurso(curso);

        List<Curso> publicados = repositorio.obtenerCursosPublicados();
        assertFalse(publicados.isEmpty());
        assertTrue(publicados.stream().anyMatch(c -> c.getId().equals(idCurso)));
    }

}
