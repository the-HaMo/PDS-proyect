package Controlador;

public class Main {
    public static void main(String[] args) {
    	System.out.println("Iniciando aplicación...");
        UsuarioController usuarioController = new UsuarioController();

        // Crear estudiantes
        System.out.println("Creando estudiantes...");
        usuarioController.crearEstudiante("Juan Pérez", "12345", "Estudiante");
        usuarioController.crearEstudiante("Ana López", "abcd", "Estudiante");
        System.out.println("Creados");
        // Mostrar todos los usuarios
        usuarioController.mostrarUsuarios();

        // Buscar usuario por nombre
        usuarioController.buscarUsuarioPorNombre("Ana López");
    }
}
