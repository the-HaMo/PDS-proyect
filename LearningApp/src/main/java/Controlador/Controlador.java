package Controlador;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Modelo.*;
import Repositorio.*;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private RepositorioUsuario repositorioUsuarios;
	private RepositorioCurso repositorioCursos;
	private RepositorioLike repositorioLikes;

	private Controlador() {
		// TODO Auto-generated constructor stub
		usuarioActual = null;
		repositorioUsuarios = new RepositorioUsuario();
		repositorioCursos = new RepositorioCurso();
		repositorioLikes = new RepositorioLike();

	}

	public boolean crearUsuario(String nombre, String contraseña, String rol) {
		if(repositorioUsuarios.obtenerUsuarioPorNombre(nombre)!=null) {
			return false;
		}
		if(rol.equals("Estudiante")) {
			Estudiante e = new Estudiante(nombre, contraseña);
			repositorioUsuarios.guardarUsuario(e);
		} else if(rol.equals("Colaborador")) {
			Colaborador c = new Colaborador(nombre, contraseña);
			repositorioUsuarios.guardarUsuario(c);
		}
		return true;

	}

	public boolean iniciarSesion(String nombre, String contraseña) {
		if (usuarioActual == null) {	
			Usuario u=repositorioUsuarios.iniciarSesion(nombre, contraseña);
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
		if(usuarioActual instanceof Colaborador) {
			curso.setAutor((Colaborador) usuarioActual);
			repositorioCursos.guardarCurso(curso);
		}
	}

	public void publicarCurso(Curso curso) {
		curso.Publicar();
		repositorioCursos.actualizarCurso(curso);
	}

	public void exportarCurso(Curso c) {
	    if (usuarioActual instanceof Estudiante) {
	        List<Curso> cursos = usuarioActual.getCursos();
	        if (!cursos.contains(c)){ 
	            c.addNumDescargas();
	            usuarioActual.addCurso(c);
	            repositorioCursos.actualizarCurso(c);
	            repositorioUsuarios.actualizarUsuario(usuarioActual);
	        }
	    }
	}

	public List<Curso> getCursosPublicados() {
		return repositorioCursos.obtenerCursosPublicados();
	}

	public List<Curso> getCursosColaborador() {
		return repositorioCursos.obtenerCursosPorAutor(usuarioActual.getId());
	}

	public List<Curso> getCursosPublicadosAutor() {
		return repositorioCursos.obtenerCursosPorAutorYPublicados(usuarioActual.getId());
	}

	public List<Curso> getCursosPrivadosAutor() {
		return repositorioCursos.obtenerCursosPorAutorPrivados(usuarioActual.getId());
	}

	private boolean yaDioLike(Curso curso) {
		return usuarioActual.getLikes().stream()
				.anyMatch(like -> like.getCurso().equals(curso));
	}

	public boolean darLike(Curso curso) {
		if (usuarioActual == null || yaDioLike(curso)) {
			return false;
		}

		curso.AddNumMeGustas();
		Like nuevoLike = new Like(usuarioActual, curso);
		usuarioActual.addLike(nuevoLike);
		repositorioLikes.guardarLike(nuevoLike);
		repositorioCursos.actualizarCurso(curso);
		repositorioUsuarios.actualizarUsuario(usuarioActual);

		return true;
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public List<Curso> getCursosEmpezados() {
		if (usuarioActual instanceof Estudiante) {
			return ((Estudiante) usuarioActual).getCursosEmpezados();
		}
		return null;
	}


	public void empezarCurso(Curso curso) {
		if (usuarioActual instanceof Estudiante) {
			List<Curso> cursosEmpezados = ((Estudiante) usuarioActual).getCursosEmpezados();
			if (!cursosEmpezados.contains(curso)) { // Verificar duplicados
				((Estudiante) usuarioActual).addCursoEmpezado(curso);
				repositorioUsuarios.actualizarUsuario(usuarioActual);
			}
		}
	}


}
