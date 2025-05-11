
package Controlador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.BloqueContenido;
import Modelo.Curso;
import Modelo.Estudiante;
import Repositorio.RepositorioCurso;
import Repositorio.RepositorioLike;
import Repositorio.RepositorioUsuario;


class ControladorTest {
	
	private RepositorioUsuario repoUsuarioMock;
	private RepositorioCurso repoCursoMock;
	private RepositorioLike repoLikeMock;
	private Controlador controlador;
		
//	@BeforeEach
//	public void setUp() throws Exception {
//		
//		controlador = Controlador.INSTANCE;
//		repoUsuarioMock = mock(RepositorioUsuario.class);
//		repoCursoMock = mock(RepositorioCurso.class);
//		repoLikeMock = mock(RepositorioLike.class); 
//		
//		injectMock(controlador, "repositorioUsuarios", repoUsuarioMock);
//        injectMock(controlador, "repositorioCursos", repoCursoMock);
//        injectMock(controlador, "repositorioLikes", repoLikeMock);
//		
//	}
//	
//	// Inyección por reflexión 
//	 private void injectMock(Object target, String fieldName, Object mock) throws Exception {
//	        Field field = target.getClass().getDeclaredField(fieldName);
//	        field.setAccessible(true);
//	        field.set(target, mock);
//	    }
//	
//	@Test
//	public void testCrearUsuarioNuevo() {
//        when(repoUsuarioMock.obtenerUsuarioPorNombre("nuevo")).thenReturn(null);
//        boolean result = controlador.crearUsuario("nuevo", "1234", "Estudiante");
//        assertTrue(result);
//        verify(repoUsuarioMock).guardarUsuario(any(Estudiante.class));
//    }

	@Test
	void testCursosEnOrdenLikes() {
		
		BloqueContenido b1 = new BloqueContenido();
		BloqueContenido b2 = new BloqueContenido();
		BloqueContenido b3 = new BloqueContenido();
		List<BloqueContenido> bl = new LinkedList<BloqueContenido>();
		bl.add(b1);
		bl.add(b2);
		bl.add(b3);
		Curso c1 = new Curso("c1", "c1", bl, 2, 3, "Español");
		Curso c2 = new Curso("c2", "c2", bl, 2, 4, "Español");
		Curso c3 = new Curso("c2", "c2", bl, 2, 6, "Español");	
		List<Curso> cursosOnline = new LinkedList<Curso>();
		cursosOnline.add(c1);
		cursosOnline.add(c2);
		cursosOnline.add(c3);
		
		List<Curso> ordenLikes = Controlador.INSTANCE.getCursosEnOrdenLikes(cursosOnline);
		assertTrue(c3.equals(ordenLikes.get(0)));
		assertTrue(c2.equals(ordenLikes.get(1)));
		assertTrue(c1.equals(ordenLikes.get(2)));
	}
	

}
