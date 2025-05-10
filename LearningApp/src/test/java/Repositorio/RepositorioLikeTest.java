package Repositorio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Like;

class RepositorioLikeTest {

    private RepositorioLike repositorioLike;

    @BeforeEach
    void LimpiarBaseDeDatos() {
       repositorioLike = new RepositorioLike();
       repositorioLike.LimpiarBaseDeDatos();
    }


/*
    @Test
    void testGuardarLike() {
        // Arrange
        Estudiante e1 = new Estudiante("Juan", "1234");
        Curso c1 = new Curso("prueba1", "Es una prueba");
        Like like = new Like(e1, c1);
        
        // Act
        repositorioLike.guardarLike(like);
        
        // Assert
        Like likePersistido = repositorioLike.buscarId(like.getId());
        assertNotNull(likePersistido, "El like debería persistirse en la base de datos");
        System.out.println(e1.getNombre() + " " + likePersistido.getUsuario().getNombre());
        assertEquals(e1.getNombre(), likePersistido.getUsuario().getNombre(), "El estudiante no coincide");
        assertEquals(c1.getNombre(), likePersistido.getCurso().getNombre(), "El curso no coincide");
        
    }
*/

    @Test
    void testGuardarLikeConEstudianteNull() {
        // Arrange
        Curso c1 = new Curso("prueba1", "Es una prueba");
        Like like = new Like(null, c1);
        
        // Act & Assert
        assertThrows(Exception.class, () -> {
            repositorioLike.guardarLike(like);
        }, "Debería lanzar excepción cuando el estudiante es null");
    }

    @Test
    void testGuardarLikeConCursoNull() {
        // Arrange
        Estudiante e1 = new Estudiante("Juan", "1234");
        Like like = new Like(e1, null);
        
        // Act & Assert
        assertThrows(Exception.class, () -> {
            repositorioLike.guardarLike(like);
        }, "Debería lanzar excepción cuando el curso es null");
    }
}

//    @Test
//    void testNoPermitirDuplicados() {
//        // Arrange
//        Estudiante e1 = new Estudiante("Juan", "1234");
//        Curso c1 = new Curso("prueba1", "Es una prueba");
//        Like like1 = new Like(e1, c1);
//        Like like2 = new Like(e1, c1); // Mismo estudiante y curso
//        
//        // Act
//        repositorioLike.guardarLike(like1);
//        
//        // Assert
//        assertThrows(Exception.class, () -> {
//            repositorioLike.guardarLike(like2);
//        }, "No debería permitir likes duplicados del mismo estudiante al mismo curso");
//    }
//}