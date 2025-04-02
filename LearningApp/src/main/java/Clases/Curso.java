package Clases;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Cursos")
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String nombre;
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "autor_id", nullable = false)
	private Colaborador autor;
	
	//private ImageIcon imagen;
	private int NumDescargas;
	private int NumMeGustas;
	
	@JsonProperty("bloques_contenidos")
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private List<BloqueContenido> bloques_contenidos;
	
	public Curso() {}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos, int NumDescargas, int NumMeGustas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.bloques_contenidos = bloques_contenidos;
		this.NumDescargas = NumDescargas;
		this.NumMeGustas = NumMeGustas;
	}
	
	public Curso(String nombre, String descripcion) {
		this(nombre, descripcion, null, 0, 0);
	}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos) {
		this(nombre, descripcion, bloques_contenidos, 0, 0);
	}
	
	
	public List<BloqueContenido> getBloquesContenidos() {
        return this.bloques_contenidos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	

	public int getNumDescargas() {
		return NumDescargas;
	}

	public void addNumDescargas() {
		NumDescargas += 1;
	}

	public int getNumMeGustas() {
		return NumMeGustas;
	}

	public void AddNumMeGustas() {
		NumMeGustas += 1;
	}
	
	public Colaborador getAutor() {
		return autor;
	}
	
	public void setAutor(Colaborador autor) {
		this.autor = autor;
	}
	
	public void setBloquesContenidos(List<BloqueContenido> bloques_contenidos) {
		this.bloques_contenidos = bloques_contenidos;
	}
	
	
}
