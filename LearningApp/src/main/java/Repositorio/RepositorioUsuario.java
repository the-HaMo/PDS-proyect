package Repositorio;

import Clases.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class RepositorioUsuario {
    
	
    private EntityManagerFactory emf;
    
	public RepositorioUsuario() {
		emf = Persistence.createEntityManagerFactory("ejemplo");
	}

    public void guardarUsuario(Usuario usuario) {
        	EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
	        em.close();
    }

    public Usuario obtenerUsuarioPorId(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class)
                                       .setParameter("nombre", nombre)
                                       .getResultList();
            return usuarios.isEmpty() ? null : usuarios.get(0);
        } finally {
            em.close();
        }
    }
    
    public Usuario iniciarSesion(String nombre, String contraseña) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Usuario> usuarios = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contraseña = :contraseña", Usuario.class)
                    .setParameter("nombre", nombre)
                    .setParameter("contraseña", contraseña)
                    .getResultList();

            return usuarios.isEmpty() ? null : usuarios.get(0);
        } finally {
            em.close();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminarUsuario(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
