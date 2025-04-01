package Controlador;
import Clases.*;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	
	private Controlador() {
		// TODO Auto-generated constructor stub
		usuarioActual = null;
	}
	
	public void crearUsuario(String nombre, String contraseña, String rol) {
		if(rol.equals("Estudiante")) {
            Estudiante e = new Estudiante(nombre, contraseña, rol);
         } else if(rol.equals("Colaborador")) {
        	Colaborador c = new Colaborador(nombre, contraseña, rol);
         }
		
		
	}
	
	public void iniciarSesion(String nombre, String contraseña) {
		if (usuarioActual == null) {
			//repositorio para sacar el usuario´
			//usuarioActual = repositorio.getUsuario(nombre, contraseña);
		}
	}
	
	public void cerrarSesion() {
		usuarioActual = null;
	}
	
	public void importarCurso(Curso curso) {
		// usuarioActual.importarCurso(curso);
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
}
