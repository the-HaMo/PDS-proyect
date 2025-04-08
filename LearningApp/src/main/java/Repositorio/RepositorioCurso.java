package Repositorio;

import Clases.Curso;
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
                "WHERE c.colaborador.id = :autor_id", Curso.class)
                .setParameter("autor_id", idAutor)
                .getResultList();
        } finally {
            em.close();
        }
    }
}
