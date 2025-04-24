package Modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	    name = "estudiante_curso",
	    joinColumns = @JoinColumn(name = "estudiante_id"),
	    inverseJoinColumns = @JoinColumn(name = "curso_id")
	)
	private List<Curso> cursosApuntados;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
	    name = "estudiante_curso_empezado",
	    joinColumns = @JoinColumn(name = "estudiante_id"),
	    inverseJoinColumns = @JoinColumn(name = "curso_id")
	)
	private List<Curso> cursosEmpezados;

	public Estudiante() {}
	
	public Estudiante(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.cursosApuntados=new ArrayList<Curso>();
        this.cursosEmpezados=new ArrayList<Curso>();
    }
	
	public List<Curso> getCursos() {//Los que tengo importado
		return cursosApuntados;
	}

	@Override
	public void addCurso(Curso curso) {
		if(!cursosApuntados.contains(curso)) {
			this.cursosApuntados.add(curso);
		}
	}

	public List<Curso> getCursosEmpezados() {//los empezados
		return cursosEmpezados;
	}

	public void addCursoEmpezado(Curso curso) {
		if (!cursosEmpezados.contains(curso)) {
			this.cursosEmpezados.add(curso);
		}
	}
	
	
}
