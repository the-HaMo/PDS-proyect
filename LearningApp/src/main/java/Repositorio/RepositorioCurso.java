package Repositorio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import Modelo.*;

public class RepositorioCurso {

    private final EntityManagerFactory emf;

    public RepositorioCurso() {
        emf = Persistence.createEntityManagerFactory("ejemplo");
    }

    public void guardarCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Curso buscarPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPublicados() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Curso c WHERE c.esPublico = true", Curso.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutor(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Curso c WHERE c.autor.id = :autor_id", Curso.class
            ).setParameter("autor_id", idAutor).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutorYPublicados(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Curso c WHERE c.autor.id = :autor_id AND c.esPublico = true", Curso.class
            ).setParameter("autor_id", idAutor).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Curso> obtenerCursosPorAutorPrivados(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT c FROM Curso c WHERE c.autor.id = :autor_id AND c.esPublico = false", Curso.class
            ).setParameter("autor_id", idAutor).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizarCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void eliminarCurso(Integer id) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Curso curso = em.find(Curso.class, id);
			if (curso != null) {
				em.remove(curso);
			}
			em.getTransaction().commit();
		} finally {
			em.close();
		}
    }
    
    public void eliminarTodo() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
            for (Curso curso : cursos) {
                em.remove(curso);  // curso está gestionado, se puede eliminar directamente
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    

}
