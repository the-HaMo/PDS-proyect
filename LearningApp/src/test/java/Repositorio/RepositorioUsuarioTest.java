package Repositorio;

import org.junit.jupiter.api.*;
import Modelo.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

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
		Estudiante e = new Estudiante("Jorge", "1234");
		repositorio.guardarUsuario(e);
		Usuario u = repositorio.iniciarSesion("Jorge", "1234");
		assertNotNull(u);
	}

	@Test
	@Order(3)
	public void testIniciarSesionFallida() {
		Estudiante e = new Estudiante("Jorge", "1234");
		repositorio.guardarUsuario(e);
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
	public void testActualizar() {
	    Estudiante e = new Estudiante("Ana", "0000");
	    repositorio.guardarUsuario(e);

	    e.setContraseña("nueva123");
	    repositorio.actualizarUsuario(e);

	    Usuario u = repositorio.iniciarSesion("Ana", "nueva123");
	    assertNotNull(u);
	}
	
	@Test
	@Order(6)
	public void testEliminarTodo() {
	    repositorio.guardarUsuario(new Estudiante("U1", "x"));
	    repositorio.guardarUsuario(new Estudiante("U2", "y"));

	    repositorio.eliminarTodo();

	    assertNull(repositorio.obtenerUsuarioPorNombre("U1"));
	    assertNull(repositorio.obtenerUsuarioPorNombre("U2"));
	}
	
	@Test
	@Order(7)
	public void testGuardarTodos() {
		Estudiante e = new Estudiante("Jorge", "1234");
		Estudiante e2 = new Estudiante("Ana", "5678");
		Estudiante e3 = new Estudiante("Luis", "abcd");
		repositorio.guardarUsuario(e);
		repositorio.guardarUsuario(e2);
		repositorio.guardarUsuario(e3);

		List<Usuario> usuarios = repositorio.obtenerTodosLosUsuarios();
		assertEquals(3, usuarios.size());
		assertTrue(usuarios.stream().anyMatch(u -> u.getNombre().equals("Jorge")));
		assertTrue(usuarios.stream().anyMatch(u -> u.getNombre().equals("Ana")));
		assertTrue(usuarios.stream().anyMatch(u -> u.getNombre().equals("Luis")));
	}
	
	@Test
	@Order(8)
	public void testGetUsuarioId() {
		Estudiante e = new Estudiante("Carlos", "1234");
		repositorio.guardarUsuario(e);

		Usuario u = repositorio.obtenerUsuarioPorId(e.getId());
		assertNotNull(u);
		assertEquals(e.getId(), u.getId());
		assertEquals(e.getNombre(), u.getNombre());
		assertEquals(e.getContraseña(), u.getContraseña());
	}

}