package Repositorio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modelo.Colaborador;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Like;

class RepositorioLikeTest {

    private RepositorioLike repositorioLike;
    private RepositorioCurso repositorioCurso;
    private RepositorioUsuario repositorioUsuario;
    
    @BeforeEach
    void InicializarTodo() {
       repositorioLike = new RepositorioLike();
       repositorioCurso = new RepositorioCurso();
       repositorioUsuario = new RepositorioUsuario();
       repositorioLike.LimpiarBaseDeDatos();
       //repositorioUsuario.eliminarTodo();
       //repositorioCurso.eliminarTodo();
    }
    
    @Test
    void testGuardarLike() {
        Estudiante e1 = new Estudiante("Juan", "1234");
        Colaborador co1 = new Colaborador("Antonio", "1234");
        Curso c1 = new Curso("prueba1", "Es una prueba");
        c1.setAutor(co1);
        repositorioUsuario.guardarUsuario(e1);
        repositorioUsuario.guardarUsuario(co1);
        repositorioCurso.guardarCurso(c1);
        Like like = new Like(e1, c1);
        repositorioLike.guardarLike(like);
        Like likePersistido = repositorioLike.buscarId(like.getId());
        assertNotNull(likePersistido);
        assertEquals(e1.getNombre(), likePersistido.getUsuario().getNombre(), "El estudiante no coincide");
        assertEquals(c1.getNombre(), likePersistido.getCurso().getNombre(), "El curso no coincide");
        
    }

    @Test
    void testGuardarLikeConEstudianteNull() {
        Curso c1 = new Curso("prueba1", "Es una prueba");
        Like like = new Like(null, c1);
        assertThrows(Exception.class, () -> {
            repositorioLike.guardarLike(like);
        }, "Debería lanzar excepción cuando el estudiante es null");
    }

    @Test
    void testGuardarLikeConCursoNull() {
        Estudiante e1 = new Estudiante("Juan", "1234");
        Like like = new Like(e1, null);
        assertThrows(Exception.class, () -> {
            repositorioLike.guardarLike(like);
        }, "Debería lanzar excepción cuando el curso es null");
    }

}