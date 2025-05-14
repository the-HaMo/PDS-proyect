package Controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.BloqueContenido;
import Modelo.Curso;
import Modelo.EstadisticaUsuario;
import Modelo.Estrategia;
import Modelo.Estudiante;
import Modelo.ProgresoCurso;
import Modelo.Usuario;


public class ControladorTestIntegracion {
	
	private Controlador controlador;
	
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
	
	@Test
	public void testImportarCurso() {
		String usuario = "userTest";
	    String password = "pass123";
	    String rol = "Colaborador";
	    controlador.crearUsuario(usuario, password, rol);
	    controlador.iniciarSesion(usuario, password);
	    Curso curso = new Curso();
	    curso.setNombre("Curso");
	    curso.setDescripcion("prueba");
	    controlador.importarCurso(curso);
	    assertNotNull(curso.getAutor());
	    assertEquals(usuario, curso.getAutor().getNombre());
	    assertTrue(controlador.getCursosColaborador().contains(curso));
	}
	
	@Test
	public void testPublicarCurso() {
		String usuario = "userTest";
	    String password = "pass123";
	    String rol = "Colaborador";
	    controlador.crearUsuario(usuario, password, rol);
	    controlador.iniciarSesion(usuario, password);
	    Curso curso = new Curso("Cursos", "prueba");
	    controlador.importarCurso(curso);
	    assertFalse(curso.getEsPublico());
	    controlador.publicarCurso(curso);
	    Curso actualizado = controlador.getCursosPublicados().get(0);
	    assertTrue(actualizado.getEsPublico());
	}
	
	@Test
	public void testExportarCurso() {
	    String colabUser = "colaborador";
	    String colabPass = "1234";
	    controlador.crearUsuario(colabUser, colabPass, "Colaborador");
	    controlador.iniciarSesion(colabUser, colabPass);
	    Curso curso = new Curso("Curso Exportable", "Descripción");
	    controlador.importarCurso(curso); 
	    controlador.publicarCurso(curso); 
	    controlador.cerrarSesion();

	    String estUser = "estudiante";
	    String estPass = "1234";
	    controlador.crearUsuario(estUser, estPass, "Estudiante");
	    controlador.iniciarSesion(estUser, estPass);
	    Estudiante estudiante = (Estudiante) controlador.getUsuarioActual();
	    assertTrue(controlador.getCursosPublicados().contains(curso));
	    controlador.exportarCurso(curso);
	    assertTrue(estudiante.getCursos().contains(curso));
	    controlador.cerrarSesion();
		
	}
	
	@Test 
	public void testGetCursosPublicados() {
		String colabUser = "colaborador";
	    String colabPass = "1234";
	    controlador.crearUsuario(colabUser, colabPass, "Colaborador");
	    controlador.iniciarSesion(colabUser, colabPass);
	    Curso c1 = new Curso("Curso Exportable", "Descripción");
	    Curso c2 = new Curso("Curso Exportable", "Descripción");
	    Curso c3 = new Curso("Curso Exportable", "Descripción");
	    controlador.importarCurso(c1);
	    controlador.importarCurso(c2);
	    controlador.importarCurso(c3);
	    controlador.publicarCurso(c1);
	    controlador.publicarCurso(c2);
	    controlador.publicarCurso(c3);
	    List<Curso> cursosPublicados = controlador.getCursosPublicados();
	    assertEquals(3, cursosPublicados.size());
	    assertEquals(cursosPublicados, List.of(c1, c2, c3));
	}
	
	@Test 
	public void testGetCursosColaborador() {
		String usuario = "colabTest";
		String password = "pass123";
		controlador.crearUsuario(usuario, password, "Colaborador");
		controlador.iniciarSesion(usuario, password);
		Curso curso1 = new Curso("Curso 1", "Descripción 1");
		Curso curso2 = new Curso("Curso 2", "Descripción 2");
		controlador.importarCurso(curso1);
		controlador.importarCurso(curso2);
		List<Curso> cursosColab = controlador.getCursosColaborador();
		assertNotNull(cursosColab);
		assertEquals(2, cursosColab.size());
		assertTrue(cursosColab.contains(curso1));
		assertTrue(cursosColab.contains(curso2));
		for (Curso curso : cursosColab) {
		    assertEquals(usuario, curso.getAutor().getNombre());
		}
		controlador.cerrarSesion();
	}
	
	@Test
	public void testGetCursosPublicadosPorAutor() {
	    
		String usuario = "colabAutor";
	    String password = "autor123";
	    controlador.crearUsuario(usuario, password, "Colaborador");
	    controlador.iniciarSesion(usuario, password);
	    Curso cursoPublicado1 = new Curso("Curso Publicado 1", "Descripción 1");
	    Curso cursoPublicado2 = new Curso("Curso Publicado 2", "Descripción 2");
	    Curso cursoNoPublicado = new Curso("Curso No Publicado", "Descripción 3");
	    controlador.importarCurso(cursoPublicado1);
	    controlador.importarCurso(cursoPublicado2);
	    controlador.importarCurso(cursoNoPublicado);
	    controlador.publicarCurso(cursoPublicado1);
	    controlador.publicarCurso(cursoPublicado2);
	    List<Curso> cursosPublicadosAutor = controlador.getCursosPublicadosAutor();
	    assertNotNull(cursosPublicadosAutor);
	    assertEquals(2, cursosPublicadosAutor.size());
	    assertTrue(cursosPublicadosAutor.contains(cursoPublicado1));
	    assertTrue(cursosPublicadosAutor.contains(cursoPublicado2));
	    assertFalse(cursosPublicadosAutor.contains(cursoNoPublicado));
	    for (Curso curso : cursosPublicadosAutor) {
	        assertTrue(curso.getEsPublico());
	        assertEquals(usuario, curso.getAutor().getNombre());
	    }
	    controlador.cerrarSesion();
	}

	@Test
	public void testGetCursosPrivadosPorAutor() {
	    
		String usuario = "colabAutor";
	    String password = "autor123";
	    controlador.crearUsuario(usuario, password, "Colaborador");
	    controlador.iniciarSesion(usuario, password);
	    Curso cursoPublicado1 = new Curso("Curso Publicado 1", "Descripción 1");
	    Curso cursoNoPublicado1 = new Curso("Curso Publicado 2", "Descripción 2");
	    Curso cursoNoPublicado2 = new Curso("Curso No Publicado", "Descripción 3");
	    controlador.importarCurso(cursoPublicado1);
	    controlador.importarCurso(cursoNoPublicado1);
	    controlador.importarCurso(cursoNoPublicado2);
	    controlador.publicarCurso(cursoPublicado1);
	    List<Curso> cursosPrivadosAutor = controlador.getCursosPrivadosAutor();
	    assertNotNull(cursosPrivadosAutor);
	    assertEquals(2, cursosPrivadosAutor.size());
	    assertTrue(cursosPrivadosAutor.contains(cursoNoPublicado1)); 
	    assertTrue(cursosPrivadosAutor.contains(cursoNoPublicado2));
	    assertFalse(cursosPrivadosAutor.contains(cursoPublicado1));
	    for (Curso curso : cursosPrivadosAutor) {
	        assertFalse(curso.getEsPublico());
	        assertEquals(usuario, curso.getAutor().getNombre());
	    }
	    controlador.cerrarSesion();
	}
	
	@Test
	public void testDarLike() {
	    String usuario = "colabLike";
	    String password = "pass123";
	    controlador.crearUsuario(usuario, password, "Colaborador");
	    controlador.iniciarSesion(usuario, password);
	    Curso curso = new Curso("Curso con Likes", "Descripción");
	    controlador.importarCurso(curso); 
	    controlador.publicarCurso(curso);
	    controlador.cerrarSesion();
	    
	    String usuarioEst = "colabLike";
	    String passwordEst = "pass123";
	    controlador.crearUsuario(usuarioEst, passwordEst, "Estudiante");
	    controlador.iniciarSesion(usuarioEst, passwordEst);
	    
	    boolean likeDado = controlador.darLike(curso);
	    assertTrue(likeDado);
	    assertEquals(1, curso.getNumMeGustas());

	    boolean likeRepetido = controlador.darLike(curso);
	    assertFalse(likeRepetido);
	    assertEquals(1, curso.getNumMeGustas());

	    controlador.cerrarSesion();
	    boolean likeSinSesion = controlador.darLike(curso);
	    assertFalse(likeSinSesion);
	}
	
	@Test
	public void testGetStatsComoEstudiante() {
	    String usuario = "estudiante1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Estudiante");
	    controlador.iniciarSesion(usuario, password);
	    
	    EstadisticaUsuario stats = controlador.getStats();
	    assertNotNull(stats);
	    assertEquals(0, stats.getTiempoUso());

	    controlador.cerrarSesion();
	}

	@Test
	public void testGetStatsComoColaborador() {
	    String usuario = "colaborador1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Colaborador");
	    controlador.iniciarSesion(usuario, password);

	    EstadisticaUsuario stats = controlador.getStats();
	    assertNull(stats);
	    controlador.cerrarSesion();
	}

	@Test
	public void testGetStatsSinSesion() {
	    EstadisticaUsuario stats = controlador.getStats();
	    assertNull(stats);
	}
	
	@Test
	public void testGetCursosEmpezadosComoEstudiante() {
	  
	    String usuario = "estudiante1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Estudiante");
	    controlador.iniciarSesion(usuario, password);
	    Estudiante estudiante = (Estudiante) controlador.getUsuarioActual();
	    Curso curso1 = new Curso("Curso 1", "Descripción 1");
	    Curso curso2 = new Curso("Curso 2", "Descripción 2");
	    curso1.setEstrategia(Estrategia.ALEATORIA);
	    curso2.setEstrategia(Estrategia.SECUENCIAL);
	    estudiante.getCursosEmpezados().put(curso1, curso1.getEstrategia());
	    estudiante.getCursosEmpezados().put(curso2, curso2.getEstrategia());

	    Map<Curso, Estrategia> cursosEmpezados = controlador.getCursosEmpezados();
	    assertNotNull(cursosEmpezados);
	    assertEquals(2, cursosEmpezados.size());
	    assertTrue(cursosEmpezados.containsKey(curso1));
	    assertEquals(Estrategia.ALEATORIA, cursosEmpezados.get(curso1));
	}

	@Test
	public void testGetCursosEmpezadosComoColaborador() {
	    controlador.crearUsuario("colaborador1", "1234", "Colaborador");
	    controlador.iniciarSesion("colaborador1", "1234");
	    Map<Curso, Estrategia> cursos = controlador.getCursosEmpezados();
	    assertNull(cursos);
	    controlador.cerrarSesion();
	}

	@Test
	public void testGetCursosEmpezadosSinSesion() {
	    Map<Curso, Estrategia> cursos = controlador.getCursosEmpezados();
	    assertNull(cursos);
	}
	
	@Test 
	public void testGetEstrategiaCursoComoEstudiante() {
	    String usuario = "estudiante1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Estudiante");
	    controlador.iniciarSesion(usuario, password);
	    
	    Curso curso = new Curso("Curso de Prueba", "Descripción");
	    Estrategia estrategiaEsperada = Estrategia.ALEATORIA;
	    curso.setEstrategia(estrategiaEsperada);
	   
	    Estudiante estudiante = (Estudiante) controlador.getUsuarioActual();
	    estudiante.getCursosEmpezados().put(curso, estrategiaEsperada);
	    Estrategia estrategiaObtenida = controlador.getEstrategiaCurso(curso);
	    assertEquals(estrategiaEsperada, estrategiaObtenida);
	}

	@Test 
	public void testGetEstrategiaCursoComoColaborador() {
	    String usuario = "colaborador1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Colaborador");
	    controlador.iniciarSesion(usuario, password);
	   
	    Curso curso = new Curso("Curso de Prueba", "Descripción");
	    assertNull(controlador.getEstrategiaCurso(curso));
	}

	@Test 
	public void testGetEstrategiaCursoSinSesion() {
	    Curso curso = new Curso("Curso de Prueba", "Descripción");
	    assertNull(controlador.getEstrategiaCurso(curso));
	}
	
	@Test
	public void testEmpezarCursoComoEstudiante() {
	    String usuario_1 = "colaborador1";
	    String password_1 = "1234";
	    controlador.crearUsuario(usuario_1, password_1, "Estudiante");
	    controlador.iniciarSesion(usuario_1, password_1);
	    
	    Curso curso = new Curso("Curso de Prueba", "Descripción");
	    Estrategia estrategia = Estrategia.ALEATORIA;
	    controlador.importarCurso(curso);
	    controlador.publicarCurso(curso);
	    controlador.cerrarSesion();
	    
	    String usuario = "estudiante1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Estudiante");
	    controlador.iniciarSesion(usuario, password);
	    
	    Curso cursoPublicado = controlador.getCursosPublicados().get(0);
	    controlador.empezarCurso(cursoPublicado, estrategia);
	    
	    Estudiante estudiante = (Estudiante) controlador.getUsuarioActual();
	    assertTrue(estudiante.getCursosEmpezados().containsKey(cursoPublicado), "El curso debería estar en los cursos empezados");  
	    assertEquals(estrategia, estudiante.getCursosEmpezados().get(cursoPublicado), "La estrategia no coincide");
	}


	@Test
	public void testEmpezarCursoComoColaborador() {
	    String usuario = "colaborador1";
	    String password = "1234";
	    controlador.crearUsuario(usuario, password, "Colaborador");
	    controlador.iniciarSesion(usuario, password);
	    
	    Curso curso = new Curso("Curso", "Descripción");
	    controlador.empezarCurso(curso, Estrategia.ALEATORIA);
	    
	    assertNull(controlador.getCursosEmpezados());
	}

	@Test
	public void testEmpezarCursoSinSesion() {
	    Curso curso = new Curso("Curso", "Descripción");
	    controlador.empezarCurso(curso, Estrategia.ALEATORIA);
	    assertNull(controlador.getCursosEmpezados());
	}
	
//	@Test
//	public void testMarcarBloqueCompletado() {
//		String usuario_1 = "colaborador1";
//	    String password_1 = "1234";
//	    controlador.crearUsuario(usuario_1, password_1, "Estudiante");
//	    controlador.iniciarSesion(usuario_1, password_1);
//		BloqueContenido b1 = new BloqueContenido("bloque 1");
//		BloqueContenido b2 = new BloqueContenido("bloque 2");
//		Curso c1 = new Curso("curso1", "tests");
//		c1.addBloque(b1);
//		c1.addBloque(b2);
//		controlador.importarCurso(c1);
//		controlador.publicarCurso(c1);
//		controlador.cerrarSesion();
//		
//	    String usuario = "estudiante1";
//	    String password = "1234";
//	    controlador.crearUsuario(usuario, password, "Estudiante");
//	    controlador.iniciarSesion(usuario, password);
//	    Curso cursoPublicado = controlador.getCursosPublicados().get(0);
//	    controlador.empezarCurso(cursoPublicado, Estrategia.ALEATORIA);
//	    
//	    Estudiante estudiante = (Estudiante) controlador.getUsuarioActual();
//	    controlador.marcarBloqueCompletado(estudiante, cursoPublicado, b1);
//	    
//	          
//		
//	}
   
}