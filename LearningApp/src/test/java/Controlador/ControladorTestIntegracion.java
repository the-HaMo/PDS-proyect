package Controlador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.Colaborador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Usuario;
import Repositorio.RepositorioUsuario;


public class ControladorTestIntegracion {
	
	private Controlador controlador;
	RepositorioUsuario repUsuarios;
	
	@BeforeEach
    public void setUp() {
		controlador = Controlador.INSTANCE;
		controlador.resetForTesting();
		
	}
    
	@Test
    public void testCrearUsuarioEstudiante() {
        String nombre = "nuevoEstudiante";
        String contraseña = "abc123";
        String rol = "Estudiante";
        assertTrue(controlador.crearUsuario(nombre, contraseña, rol));
        assertFalse(controlador.crearUsuario(nombre, contraseña, rol));
        Usuario u = controlador.obtenerUsuarioPorNombre(nombre);
        assertEquals(nombre, u.getNombre());
    }
	
	@Test
    public void testCrearUsuarioColaborador() {
        String nombre = "nuevoColaborador";
        String contraseña = "abc123";
        String rol = "Colaborador";
        assertTrue(controlador.crearUsuario(nombre, contraseña, rol));
        assertFalse(controlador.crearUsuario(nombre, contraseña, rol));
        Usuario u = controlador.obtenerUsuarioPorNombre(nombre);
        assertEquals(nombre, u.getNombre());
    }
	
	@Test
	public void testIniciarSesionExito() {
		String usuario = "user1";
		String password = "user1";
		controlador.crearUsuario(usuario, password, "Estudiante");
		assertTrue(controlador.iniciarSesion(usuario, password));
		assertNotNull(controlador.getUsuarioActual());
		assertEquals(usuario, controlador.getUsuarioActual().getNombre());
	}
	
	@Test
	public void testIniciarSesionFallo() {
		String usuario = "user1";
		String password = "user1";
		String passwordFail = "user";
		controlador.crearUsuario(usuario, password, "Colaborador");
		assertFalse(controlador.iniciarSesion(usuario, passwordFail));
		assertNull(controlador.getUsuarioActual());
	}
    
	@Test 
	public void testCerrarSesionEstudiante() {
		 String usuario = "userTest";
	     String password = "pass123";
	     String rol = "Estudiante";
	     controlador.crearUsuario(usuario, password, rol);
	     assertTrue(controlador.iniciarSesion(usuario, password));
	     assertNotNull(controlador.getUsuarioActual());    
	     controlador.cerrarSesion();
	     assertNull(controlador.getUsuarioActual());
	     Estudiante est = (Estudiante) controlador.obtenerUsuarioPorNombre(usuario);
	     assertNotNull(est.getStats());
	     assertTrue(est.getStats().getTiempoUso() >= 0);
	}
	
	@Test 
	public void testCerrarSesionColaborador() {
		 String usuario = "userTest";
	     String password = "pass123";
	     String rol = "Colaborador";
	     controlador.crearUsuario(usuario, password, rol);
	     assertTrue(controlador.iniciarSesion(usuario, password));
	     assertNotNull(controlador.getUsuarioActual());    
	     controlador.cerrarSesion();
	     assertNull(controlador.getUsuarioActual());
	}
	
//	@Test
//	public void testImportarCurso() {
//		String usuario = "userTest";
//	    String password = "pass123";
//	    String rol = "Colaborador";
//	    controlador.crearUsuario(usuario, password, rol);
//	    controlador.iniciarSesion(usuario, password);
//	    Curso curso = new Curso();
//	    curso.setNombre("Curso");
//	    curso.setDescripcion("prueba");
//	    controlador.importarCurso(curso);
//	    assertNotNull(curso.getAutor());
//	    assertEquals(usuario, curso.getAutor().getNombre());
//	    assertTrue(controlador.getCursosColaborador().contains(curso));
//	}
//	
	
   
}