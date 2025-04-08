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

    public Usuario obtenerUsuarioPorNombre(String n) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class)
                                       .setParameter("nombre", n)
                                       .getResultList();
            return usuarios.isEmpty() ? null : usuarios.get(0);
        } finally {
            em.close();
        }
    }
    
    public Usuario iniciarSesion(String n, String c) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Usuario> usuarios = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contrasena = :contrasena", Usuario.class)
                    .setParameter("nombre", n)
                    .setParameter("contrasena", c)
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
    
    public void actualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
