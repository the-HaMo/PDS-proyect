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

	public Estudiante() {}
	
	public Estudiante(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.cursosApuntados=new ArrayList<Curso>();
    }
	
	public List<Curso> getCursos() {
		return cursosApuntados;
	}

	@Override
	public void addCurso(Curso curso) {
		this.cursosApuntados.add(curso);
    
	}
}
