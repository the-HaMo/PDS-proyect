package Clases;

import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("COLABORADOR")
public class Colaborador extends Usuario {

	@OneToMany(mappedBy = "autor")
	private List<Curso> cursos_Creados;
	
	public Colaborador() {}
	
	public Colaborador(String nombre, String contraseña) {
		super(nombre, contraseña);
	}
	
	public void añadirCurso(Curso curso) {
		this.cursos_Creados.add(curso);
	}

	
	public List<Curso> getCursosCreados() {
		return this.cursos_Creados;
	}

}
