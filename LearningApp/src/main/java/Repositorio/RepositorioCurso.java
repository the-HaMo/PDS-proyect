package Repositorio;

import Clases.Curso;
import Clases.BloqueContenido;
import Clases.Pregunta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class RepositorioCurso {
    private EntityManagerFactory emf;

    public RepositorioCurso() {
        emf = Persistence.createEntityManagerFactory("ejemplo");
    }

    public void guardarCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
    }

    public Curso buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            // Cargar solo el curso sin los bloques y preguntas
            Curso curso = em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "WHERE c.id = :id", Curso.class)
                .setParameter("id", id)
                .getSingleResult();

            // Cargar los bloques de contenido para el curso
            cargarBloquesDeContenidoParaCurso(curso, em);

            // Cargar las preguntas para cada bloque
            for (BloqueContenido bloque : curso.getBloquesContenidos()) {
                cargarPreguntasParaBloque(bloque, em);
            }

            return curso;
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            // Cargar solo los cursos sin bloques ni preguntas
            List<Curso> cursos = em.createQuery(
                "SELECT DISTINCT c FROM Curso c", Curso.class)
                .getResultList();

            // Cargar los bloques y preguntas de cada curso
            for (Curso curso : cursos) {
                cargarBloquesDeContenidoParaCurso(curso, em);
                for (BloqueContenido bloque : curso.getBloquesContenidos()) {
                    cargarPreguntasParaBloque(bloque, em);
                }
            }

            return cursos;
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPublicados() {
        EntityManager em = emf.createEntityManager();
        try {
            // Cargar solo los cursos publicados sin bloques ni preguntas
            List<Curso> cursos = em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "WHERE c.publicado = true", Curso.class)
                .getResultList();

            // Cargar los bloques y preguntas de cada curso
            for (Curso curso : cursos) {
                cargarBloquesDeContenidoParaCurso(curso, em);
                for (BloqueContenido bloque : curso.getBloquesContenidos()) {
                    cargarPreguntasParaBloque(bloque, em);
                }
            }

            return cursos;
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutor(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            // Cargar solo los cursos del autor sin bloques ni preguntas
            List<Curso> cursos = em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "WHERE c.autor.id = :autor_id", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();

            // Cargar los bloques y preguntas de cada curso
            for (Curso curso : cursos) {
                cargarBloquesDeContenidoParaCurso(curso, em);
                for (BloqueContenido bloque : curso.getBloquesContenidos()) {
                    cargarPreguntasParaBloque(bloque, em);
                }
            }

            return cursos;
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutorYPublicados(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            // Cargar solo los cursos del autor que son públicos sin bloques ni preguntas
            List<Curso> cursos = em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "WHERE c.autor.id = :autor_id AND c.esPublico = true", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();

            // Cargar los bloques y preguntas de cada curso
            for (Curso curso : cursos) {
                cargarBloquesDeContenidoParaCurso(curso, em);
                for (BloqueContenido bloque : curso.getBloquesContenidos()) {
                    cargarPreguntasParaBloque(bloque, em);
                }
            }

            return cursos;
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutorPrivados(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            // Cargar solo los cursos del autor que no son públicos sin bloques ni preguntas
            List<Curso> cursos = em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "WHERE c.autor.id = :autor_id AND c.esPublico = false", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();

            // Cargar los bloques y preguntas de cada curso
            for (Curso curso : cursos) {
                cargarBloquesDeContenidoParaCurso(curso, em);
                for (BloqueContenido bloque : curso.getBloquesContenidos()) {
                    cargarPreguntasParaBloque(bloque, em);
                }
            }

            return cursos;
        } finally {
            em.close();
        }
    }

    public void actualizarCurso(Curso c) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Métodos adicionales para cargar los bloques y preguntas

    private void cargarBloquesDeContenidoParaCurso(Curso curso, EntityManager em) {
        List<BloqueContenido> bloques = em.createQuery(
            "SELECT b FROM BloqueContenido b WHERE b.curso.id = :cursoId", BloqueContenido.class)
            .setParameter("cursoId", curso.getId())
            .getResultList();

        curso.setBloquesContenidos(bloques);
    }

    private void cargarPreguntasParaBloque(BloqueContenido bloque, EntityManager em) {
        List<Pregunta> preguntas = em.createQuery(
            "SELECT p FROM Pregunta p WHERE p.bloqueContenido.id = :bloqueId", Pregunta.class)
            .setParameter("bloqueId", bloque.getId())
            .getResultList();

        bloque.setPreguntas(preguntas);
    }
    
}
