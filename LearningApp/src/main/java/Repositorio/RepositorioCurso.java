package Repositorio;

import Clases.Curso;
import Clases.Usuario;
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
            return em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.bloques_contenidos b " +
                "LEFT JOIN FETCH b.preguntas " +
                "WHERE c.id = :id", Curso.class)
                .setParameter("id", id)
                .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.bloques_contenidos b " +
                "LEFT JOIN FETCH b.preguntas", Curso.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPublicados() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.bloques_contenidos b " +
                "LEFT JOIN FETCH b.preguntas " +
                "WHERE c.publicado = true", Curso.class)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutor(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.bloques_contenidos b " +
                "LEFT JOIN FETCH b.preguntas " +
                "WHERE c.autor.id = :autor_id", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Curso> obtenerCursosPorAutorYPublicados(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.bloques_contenidos b " +
                "LEFT JOIN FETCH b.preguntas " +
                "WHERE c.autor.id = :autor_id AND c.publicado = true", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutorPrivados(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT DISTINCT c FROM Curso c " +
                "LEFT JOIN FETCH c.bloques_contenidos b " +
                "LEFT JOIN FETCH b.preguntas " +
                "WHERE c.autor.id = :autor_id AND c.publicado = false", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();
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

}
