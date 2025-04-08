package Repositorio;

import Clases.Curso;
import Clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class RepositorioCurso {
    private EntityManagerFactory emf;
    private EntityManager em;

    public RepositorioCurso() {
        emf = Persistence.createEntityManagerFactory("ejemplo");
    }

    public void guardarCurso(Curso curso) {
    	em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
        
    }

    public Curso buscarPorId(Integer id) {
    	em = emf.createEntityManager();
		try {
			return em.find(Curso.class, id);
		} finally {
			em.close();
		}
    }

    public List<Curso> obtenerTodos() {
    	em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
		} finally {
			em.close();
		}
    }

    
    public List<Curso> obtenerCursosPublicados() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Curso c WHERE c.publicado = true", Curso.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Curso> obtenerCursosPorAutor(Integer idAutor) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Curso c WHERE c.colaborador.id = :autor_id", Curso.class)
                     .setParameter("autor_id", idAutor)
                     .getResultList();
        } finally {
            em.close();
        }
    }


}
