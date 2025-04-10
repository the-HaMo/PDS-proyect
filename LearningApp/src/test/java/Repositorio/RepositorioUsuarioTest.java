package Repositorio;

import org.junit.jupiter.api.*;

import Modelo.Estudiante;
import Modelo.Usuario;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioUsuarioTest {

    static RepositorioUsuario repositorio;

    @BeforeAll
    public static void setup() {
        repositorio = new RepositorioUsuario();
    }

    @Test
    @Order(1)
    public void testGuardarYBuscarUsuario() {
        Estudiante e = new Estudiante("Jorge", "1234");
        repositorio.guardarUsuario(e);

        Usuario u = repositorio.obtenerUsuarioPorNombre("Jorge");
        assertNotNull(u);
        assertEquals("Jorge", u.getNombre());
    }

    @Test
    @Order(2)
    public void testIniciarSesionExitosa() {
        Usuario u = repositorio.iniciarSesion("Jorge", "1234");
        assertNotNull(u);
        assertEquals("Jorge", u.getNombre());
    }

    @Test
    @Order(3)
    public void testIniciarSesionFallida() {
        Usuario u = repositorio.iniciarSesion("Jorge", "wrong");
        assertNull(u);
    }

    @Test
    @Order(4)
    public void testEliminarUsuario() {
        Usuario u = repositorio.obtenerUsuarioPorNombre("Jorge");
        assertNotNull(u);

        repositorio.eliminarUsuario(u.getId());

        Usuario eliminado = repositorio.obtenerUsuarioPorId(u.getId());
        assertNull(eliminado);
    }
}
