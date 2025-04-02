package Controlador;
import Clases.*;
import Repositorio.RepositorioUsuario;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private RepositorioUsuario repositorio;
	
	private Controlador() {
		// TODO Auto-generated constructor stub
		usuarioActual = null;
		repositorio = new RepositorioUsuario();
		
	}
	
	public void crearUsuario(String nombre, String contraseña, String rol) {
		if(rol.equals("Estudiante")) {
            Estudiante e = new Estudiante(nombre, contraseña);
            repositorio.guardarUsuario(e);
         } else if(rol.equals("Colaborador")) {
        	Colaborador c = new Colaborador(nombre, contraseña);
        	repositorio.guardarUsuario(c);
         }
		
		
		
	}
	
	public void iniciarSesion(String nombre, String contraseña) {
		if (usuarioActual == null) {	
			Usuario u=repositorio.iniciarSesion(nombre, contraseña);
			if(u!=null) {
                usuarioActual = u;
			}
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
