package Repositorio;

import Modelo.Like;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class RepositorioLike {

	 private final EntityManagerFactory emf;

	    public RepositorioLike() {
	        emf = Persistence.createEntityManagerFactory("ejemplo");
	    }
	    
    public void guardarLike(Like like) {
    	EntityManager em = emf.createEntityManager();
    	try {
        em.getTransaction().begin();
        em.persist(like);
        em.getTransaction().commit();
    	} finally {
    		em.close();
    	}
    }
    
    public void LimpiarBaseDeDatos() {
    	EntityManager em = emf.createEntityManager();
    	try {
    		em.getTransaction().begin();
    		Query query = em.createQuery("DELETE FROM Like");
    		query.executeUpdate();
    		em.getTransaction().commit();
    	} finally {
			em.close();
		}
  
    }
    
    public Like buscarId(Integer id) {
    	EntityManager em = emf.createEntityManager();
    	try {
    		return em.find(Like.class, id);
    	} finally {
    		em.close();
    	}
    }
    
}
