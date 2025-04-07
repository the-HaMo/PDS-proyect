package Controlador;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public boolean crearUsuario(String nombre, String contraseña, String rol) {
		if(repositorio.obtenerUsuarioPorNombre(nombre)!=null) {
            return false;
		}
		if(rol.equals("Estudiante")) {
            Estudiante e = new Estudiante(nombre, contraseña);
            repositorio.guardarUsuario(e);
         } else if(rol.equals("Colaborador")) {
        	Colaborador c = new Colaborador(nombre, contraseña);
        	repositorio.guardarUsuario(c);
         }
		return true;
		
	}
	
	public boolean iniciarSesion(String nombre, String contraseña) {
		if (usuarioActual == null) {	
			Usuario u=repositorio.iniciarSesion(nombre, contraseña);
			if(u!=null) {
                 usuarioActual = u;
                 return true;
			}
		}
		return false;
	}
	
	public List<Curso> getCursosEnOrdenLikes(List<Curso> cursosOnline){
		return cursosOnline.stream()
				.sorted(Comparator.comparing(Curso::getNumMeGustas).reversed())
				.collect(Collectors.toList());
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
