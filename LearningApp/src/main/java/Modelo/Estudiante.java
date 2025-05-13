package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{

	@ManyToMany(fetch = FetchType.EAGER)
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
	
	@OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private EstadisticaUsuario stats;
	
	public Estudiante() {}
	
	public Estudiante(String nombre, String contraseña) {
        super(nombre, contraseña);
        this.cursosApuntados=new ArrayList<Curso>();
        this.cursosEmpezados= new HashMap<Curso, Estrategia>();
        this.stats = new EstadisticaUsuario(this);
    }
	
	public List<Curso> getCursos() {//Los que tengo importado
		return new ArrayList<Curso>(this.cursosApuntados);
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
	
	public Estrategia getEstrategia(Curso curso) {
		return cursosEmpezados.get(curso);
	}

	public EstadisticaUsuario getStats() {
		return stats;
	}

	public void setStats(EstadisticaUsuario stats) {
		this.stats = stats;
	}

	public void addCursoEmpezado(Curso curso, Estrategia estrategia) {
		if (!cursosEmpezados.containsKey(curso)) {
	        cursosEmpezados.put(curso, estrategia);
	    }
	}
	
}
	

