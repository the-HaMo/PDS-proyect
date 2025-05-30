package Controlador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import Modelo.*;
import Repositorio.*;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private RepositorioUsuario repositorioUsuarios;
	private RepositorioCurso repositorioCursos;
	private RepositorioLike repositorioLikes;
	private Curso cursoActual;


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
				if (u instanceof Estudiante) {
					Estudiante estudiante = (Estudiante) u;
					if (estudiante.getStats() == null) {
						EstadisticaUsuario nuevaStats = new EstadisticaUsuario(estudiante);
						estudiante.setStats(nuevaStats);
						repositorioUsuarios.actualizarUsuario(estudiante);
					}
					estudiante.getStats().iniciarTiempo();
				}
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
	
	public List<Curso> getCursosEnOrdenDescargas(List<Curso> cursosOnline){
		return cursosOnline.stream()
				.sorted(Comparator.comparing(Curso::getNumDescargas).reversed())
				.collect(Collectors.toList());
	}

	public void cerrarSesion() {
		if (usuarioActual instanceof Estudiante) {
			Estudiante est = (Estudiante) usuarioActual;
			EstadisticaUsuario stats = est.getStats();
			if (stats != null) {
				stats.finalizarTiempo();
				repositorioUsuarios.actualizarUsuario(est);
			}
		}
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

	public void exportarCurso(Curso c) { // o DescargarCurso
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
	
	public EstadisticaUsuario getStats() {
		if (usuarioActual instanceof Estudiante student) {
			return student.getStats();
		}
		return null;
	}

	public Map<Curso, Estrategia> getCursosEmpezados() {
		if (usuarioActual instanceof Estudiante) {
			return ((Estudiante) usuarioActual).getCursosEmpezados();
		}
		return null;
	}
	
	
	public Estrategia getEstrategiaCurso(Curso curso) {
		if (usuarioActual instanceof Estudiante) {
			return ((Estudiante) usuarioActual).getEstrategia(curso);
		}
		return null;
	}
	
	public void actualizarCursosUsuario(List<Curso> cursosActualizados) {
	    this.usuarioActual.setCursos(cursosActualizados);
	    repositorioUsuarios.actualizarUsuario(usuarioActual);
	}
	
	public Curso getCursoActual() {
	    return cursoActual;
	}

	public void setCursoActual(Curso curso) {
	    this.cursoActual = curso;
	}
	
	public Usuario obtenerUsuarioPorNombre(String nombre) {
	    return repositorioUsuarios.obtenerUsuarioPorNombre(nombre);
	}

	public void empezarCurso(Curso curso, Estrategia estrategia) {
		if (usuarioActual instanceof Estudiante) {
			Map<Curso,Estrategia> cursosEmpezados = ((Estudiante) usuarioActual).getCursosEmpezados();
			if (!cursosEmpezados.containsKey(curso)) { // Verificar duplicados
				((Estudiante) usuarioActual).addCursoEmpezado(curso, estrategia);
				repositorioUsuarios.actualizarUsuario(usuarioActual);
			}
		}
	}
	
	public void actualizarCurso(Curso curso) {
		repositorioCursos.actualizarCurso(curso);
	}

	public void marcarBloqueCompletado(Estudiante estudiante, Curso curso, BloqueContenido bloque) {
	    ProgresoCurso progreso = new ProgresoCurso(estudiante, curso, bloque);
	    RepositorioProgresoCurso.guardar(progreso);
	}
	
	public List<Pregunta> obtenerPreguntasSecuencial(Curso curso) {
	    return curso.getBloquesContenidos().stream()
	                .flatMap(b -> b.getPreguntas().stream())
	                .collect(Collectors.toList());
	}

	public List<Pregunta> obtenerPreguntasAleatoria(Curso curso) {
	    List<Pregunta> preguntas = obtenerPreguntasSecuencial(curso);
	    Collections.shuffle(preguntas);
	    return preguntas;
	}

	public List<Pregunta> obtenerPreguntasRepeticionEspaciada(Curso curso) {
	    List<Pregunta> original = obtenerPreguntasSecuencial(curso);
	    List<Pregunta> espaciadas = new ArrayList<>();
	    Random rand = new Random();
	    int intervalos = rand.nextInt(4) + 2;

	    for (int i = 0; i < original.size(); i++) {
	        Pregunta p = original.get(i);
	        espaciadas.add(p);
	        if ((i + 1) % intervalos == 0) {
	            espaciadas.addAll(original.subList(0, i + 1));
	        }
	    }

	    return espaciadas;
	}
	
	public void resetForTesting() {
	    this.repositorioUsuarios = new RepositorioUsuario();
	    this.repositorioCursos = new RepositorioCurso();
	    this.repositorioLikes = new RepositorioLike();
	    this.usuarioActual = null;
	    this.cursoActual = null;
	    repositorioCursos.eliminarTodo();
	    repositorioUsuarios.eliminarTodo();   
	    repositorioLikes.LimpiarBaseDeDatos();
	}
	
	
}
