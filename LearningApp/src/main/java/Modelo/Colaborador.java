package Modelo;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("COLABORADOR")
public class Colaborador extends Usuario {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "autor_id")
	private List<Curso> cursos_Creados;
	
	public Colaborador() {}
	
	public Colaborador(String nombre, String contraseña) {
		super(nombre, contraseña);
	}


	@Override
	public void addCurso(Curso curso) {
	cursos_Creados.add(curso);	
	}

	@Override
	public List<Curso> getCursos() {
		return this.cursos_Creados;
	}
	
	@Override
	public void setCursos(List<Curso> cursos) {
	    this.cursos_Creados = cursos;
	}


}
