package Clases;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("COLABORADOR")
public class Colaborador extends Usuario {

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "autor_id")
	private List<Curso> cursos_Creados;
	
	public Colaborador() {}
	
	public Colaborador(String nombre, String contraseña) {
		super(nombre, contraseña);
	}

	
	public List<Curso> getCursosCreados() {
		return this.cursos_Creados;
	}

	@Override
	public void addCurso(Curso curso) {
	cursos_Creados.add(curso);	
	}

}
