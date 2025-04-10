package Repositorio;

import java.util.List;

import Modelo.Pregunta;
import Modelo.PreguntaRellenarHuecos;
import Modelo.PreguntaTest;
import Modelo.PreguntaTraduccion;
import jakarta.persistence.*;

public class RepositorioPregunta {

	private EntityManagerFactory emf;
	
	public RepositorioPregunta() {
		emf = Persistence.createEntityManagerFactory("ejemplo");
	}
	 
	public void guardarPregunta(Pregunta pregunta) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(pregunta);
		em.getTransaction().commit();
		em.close();
	}
	
	public Pregunta obtenerPreguntaPorId(Integer id) {
		EntityManager em = emf.createEntityManager();
		Pregunta p = em.find(Pregunta.class, id);
		em.close();
		return p;	
	}
	
	public List<Pregunta> obtenerTodas() {
        EntityManager em = emf.createEntityManager();
        List<Pregunta> preguntas = em.createQuery("SELECT p FROM Pregunta p", Pregunta.class)
                                     .getResultList();
        em.close();
        return preguntas;
    }
	
	public void eliminarPreguntaPorId (Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Pregunta p = em.find(Pregunta.class, id);
		if( p != null ) {
			em.remove(p);
		}
		em.getTransaction().commit();		
		em.close();
	}
	
	// 🔎 Obtener preguntas por tipo
    public List<PreguntaTest> obtenerPreguntasTest() {
        EntityManager em = emf.createEntityManager();
        List<PreguntaTest> preguntas = em.createQuery("SELECT p FROM PreguntaTest p", PreguntaTest.class)
                                         .getResultList();
        em.close();
        return preguntas;
    }

    public List<PreguntaTraduccion> obtenerPreguntasTraduccion() {
        EntityManager em = emf.createEntityManager();
        List<PreguntaTraduccion> preguntas = em.createQuery("SELECT p FROM PreguntaTraduccion p", PreguntaTraduccion.class)
                                               .getResultList();
        em.close();
        return preguntas;
    }

    public List<PreguntaRellenarHuecos> obtenerPreguntasRellenarHuecos() {
        EntityManager em = emf.createEntityManager();
        List<PreguntaRellenarHuecos> preguntas = em.createQuery("SELECT p FROM PreguntaRellenarHuecos p", PreguntaRellenarHuecos.class)
                                                   .getResultList();
        em.close();
        return preguntas;
    }
	
}
