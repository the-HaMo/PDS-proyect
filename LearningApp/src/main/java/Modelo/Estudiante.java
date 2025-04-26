package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
	
	@ElementCollection(fetch = FetchType.EAGER)
	   @CollectionTable(
	       name = "estudiante_cursos_empezados",
	       joinColumns = @JoinColumn(name = "estudiante_id")
	   )
	   @MapKeyJoinColumn(name = "curso_id")
	   @Column(name = "estrategia")
	   @Enumerated(EnumType.STRING)
	   private Map<Curso, Estrategia> cursosEmpezados;

	public Estudiante() {}
	
	public Estudiante(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.cursosApuntados=new ArrayList<Curso>();
        this.cursosEmpezados= new HashMap<Curso, Estrategia>();
    }
	
	public List<Curso> getCursos() {//Los que tengo importado
		return this.cursosApuntados;
	}

	@Override
	public void addCurso(Curso curso) {
		if(!cursosApuntados.contains(curso)) {
			this.cursosApuntados.add(curso);
		}
	}

	public void setCursos(List<Curso> cursos) {
	    this.cursosApuntados = cursos;
	}

	
	public Map<Curso, Estrategia> getCursosEmpezados() {//los empezados
		return cursosEmpezados;
	}

	public void addCursoEmpezado(Curso curso, Estrategia estrategia) {
		if (!cursosEmpezados.containsKey(curso)) {
	        cursosEmpezados.put(curso, estrategia);
	    }
	}
	
}
	

