package Repositorio;

import Clases.Estudiante;
import Clases.Usuario;
import org.junit.jupiter.api.*;


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
        Estudiante e = new Estudiante("prueba1", "pass1");
        repositorio.guardarUsuario(e);

        Usuario u = repositorio.obtenerUsuarioPorNombre("prueba1");
        assertNotNull(u);
        assertEquals("prueba1", u.getNombre());
    }

    @Test
    @Order(2)
    public void testIniciarSesionExitosa() {
        Usuario u = repositorio.iniciarSesion("prueba1", "pass1");
        assertNotNull(u);
        assertEquals("prueba1", u.getNombre());
    }

    @Test
    @Order(3)
    public void testIniciarSesionFallida() {
        Usuario u = repositorio.iniciarSesion("prueba1", "wrong");
        assertNull(u);
    }

    @Test
    @Order(4)
    public void testEliminarUsuario() {
        Usuario u = repositorio.obtenerUsuarioPorNombre("prueba1");
        assertNotNull(u);

        repositorio.eliminarUsuario(u.getId());

        Usuario eliminado = repositorio.obtenerUsuarioPorId(u.getId());
        assertNull(eliminado);
    }
}
