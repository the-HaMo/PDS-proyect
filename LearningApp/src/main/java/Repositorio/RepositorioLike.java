package Repositorio;

import Modelo.Like;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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
}
