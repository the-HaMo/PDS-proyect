package Prueba;

import jakarta.persistence.*;
import Clases.*;

public class Main {
    public static void main(String[] args) {
    	
    	System.out.println("HOLA MUNDO");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ejemplo");
        EntityManager em = emf.createEntityManager();
        
        System.out.println("Prueba de persistencia");
        em.getTransaction().begin();
        System.out.println("Prueba de persistencia 2");
        // Prueba creando un colaborador
        Colaborador colab = new Colaborador("Juan", "1234");
        em.persist(colab);

        // Prueba creando un curso
        Curso curso = new Curso("Java Básico", "Curso de introducción a Java");
        curso.setAutor(colab);
        em.persist(curso);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
