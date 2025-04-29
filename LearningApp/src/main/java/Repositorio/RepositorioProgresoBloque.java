package Repositorio;

import Modelo.BloqueContenido;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.ProgresoBloque;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class RepositorioProgresoBloque {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");

    public static void guardar(ProgresoBloque progreso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(progreso);
        em.getTransaction().commit();
        em.close();
    }

    public static List<ProgresoBloque> buscarPorEstudianteYCurso(Estudiante estudiante, Curso curso) {
        EntityManager em = emf.createEntityManager();
        List<ProgresoBloque> progresos = em.createQuery(
            "SELECT p FROM ProgresoBloque p WHERE p.estudiante.id = :estudianteId AND p.curso.id = :cursoId", ProgresoBloque.class)
            .setParameter("estudianteId", estudiante.getId())
            .setParameter("cursoId", curso.getId())
            .getResultList();
        em.close();
        return progresos;
    }

    public static boolean estaCompletado(Estudiante estudiante, Curso curso, BloqueContenido bloque) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery(
            "SELECT COUNT(p) FROM ProgresoBloque p " +
            "WHERE p.estudiante.id = :estudianteId " +
            "AND p.curso.id = :cursoId " +
            "AND p.bloque.id = :bloqueId", Long.class)
            .setParameter("estudianteId", estudiante.getId())
            .setParameter("cursoId", curso.getId())
            .setParameter("bloqueId", bloque.getId())
            .getSingleResult();
        em.close();
        return count > 0;
    }
}
