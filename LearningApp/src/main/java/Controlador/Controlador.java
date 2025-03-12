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
		//Usuario u = new Usuario(nombre, contraseña, rol);
		
	}
}
