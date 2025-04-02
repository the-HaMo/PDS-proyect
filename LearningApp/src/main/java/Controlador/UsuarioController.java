package Controlador;

import Clases.*;
import Repositorio.RepositorioUsuario;
import java.util.List;

public class UsuarioController {

    private final RepositorioUsuario usuarioRepository;

    // Constructor
    public UsuarioController() {
        usuarioRepository = new RepositorioUsuario(); // No estático para permitir flexibilidad
    }

    // Crear un estudiante
    public void crearEstudiante(String nombre, String contraseña) {
        Estudiante estudiante = new Estudiante(nombre, contraseña); // Crear un Estudiante
        usuarioRepository.guardarUsuario(estudiante); // Guardar en el repositorio
        System.out.println("Estudiante creado con éxito.");
    }

    // Mostrar todos los usuarios
    public void mostrarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.obtenerTodosLosUsuarios(); // Obtener todos los usuarios
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " - Nombre: " + usuario.getNombre());
        }
    }

    // Buscar un usuario por nombre
    public Usuario buscarUsuarioPorNombre(String nombre) {
        Usuario usuario = usuarioRepository.obtenerUsuarioPorNombre(nombre); // Buscar usuario por nombre
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            System.out.println("Usuario encontrado: " + usuario.getNombre());
        }
        return usuario;
    }

    // Eliminar un usuario por ID
    public void eliminarUsuario(Integer id) {
        usuarioRepository.eliminarUsuario(id); // Eliminar usuario del repositorio
        System.out.println("Usuario eliminado con éxito.");
    }
}
