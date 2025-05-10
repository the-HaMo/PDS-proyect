package Repositorio;

import org.junit.jupiter.api.*;

import Modelo.Colaborador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Usuario;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositorioUsuarioTest {

	static RepositorioUsuario repositorio;

	@BeforeAll
	public static void setup() {
		repositorio = new RepositorioUsuario();
		repositorio.eliminarTodo();
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


	@Test
	@Order(5)
	public void testActualizarContrasena() {
	    Estudiante e = new Estudiante("Ana", "0000");
	    repositorio.guardarUsuario(e);

	    e.setContraseña("nueva123");
	    repositorio.actualizarUsuario(e);

	    Usuario u = repositorio.iniciarSesion("Ana", "nueva123");
	    assertNotNull(u);
	}
	
	@Test
	@Order(8)
	public void testEliminarTodo() {
	    repositorio.guardarUsuario(new Estudiante("U1", "x"));
	    repositorio.guardarUsuario(new Estudiante("U2", "y"));

	    repositorio.eliminarTodo();

	    assertNull(repositorio.obtenerUsuarioPorNombre("U1"));
	    assertNull(repositorio.obtenerUsuarioPorNombre("U2"));
	}

}

