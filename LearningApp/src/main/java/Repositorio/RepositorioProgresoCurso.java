package Repositorio;

import Modelo.BloqueContenido;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.ProgresoCurso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class RepositorioProgresoCurso {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");

    public static void guardar(ProgresoCurso progreso) {
        EntityManager em = emf.createEntityManager();
        try {
	        em.getTransaction().begin();
	        em.persist(progreso);
	        em.getTransaction().commit();
        } finally {
        	em.close();
        }
    }

    public static List<ProgresoCurso> buscarPorEstudianteYCurso(Estudiante estudiante, Curso curso) {
        EntityManager em = emf.createEntityManager();
        List<ProgresoCurso> progresos = new ArrayList<>();
        try {
            progresos = em.createQuery(
            "SELECT p FROM ProgresoCurso p WHERE p.estudiante.id = :estudianteId AND p.curso.id = :cursoId", ProgresoCurso.class)
            .setParameter("estudianteId", estudiante.getId())
            .setParameter("cursoId", curso.getId())
            .getResultList();
        } finally {
        	 em.close();
        }
        return progresos;
    }

    public static boolean estaCompletado(Estudiante estudiante, Curso curso, BloqueContenido bloque) {
        EntityManager em = emf.createEntityManager();
        Long count;
        try {
        	count = em.createQuery(
            "SELECT COUNT(p) FROM ProgresoCurso p " +
            "WHERE p.estudiante.id = :estudianteId " +
            "AND p.curso.id = :cursoId " +
            "AND p.bloque.id = :bloqueId", Long.class)
            .setParameter("estudianteId", estudiante.getId())
            .setParameter("cursoId", curso.getId())
            .setParameter("bloqueId", bloque.getId())
            .getSingleResult();
        } finally {
        em.close();
        }
        return count > 0;
    }
}
