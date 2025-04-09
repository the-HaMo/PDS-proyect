package Clases;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
	@JsonProperty("idioma")
	private String idioma;
	
	@ManyToOne
	@JoinColumn(name = "autor_id", nullable = false)
	private Colaborador autor;
	
	private int NumDescargas;
	private int NumMeGustas;
	
	private boolean esPublico;
	
	@JsonProperty("bloques_contenidos")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "curso_id")
	private List<BloqueContenido> bloques_contenidos;
	
	public Curso() {
		this.bloques_contenidos = new LinkedList<BloqueContenido>();
	}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos, int NumDescargas, int NumMeGustas, String idioma) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.bloques_contenidos = bloques_contenidos;
		this.NumDescargas = NumDescargas;
		this.NumMeGustas = NumMeGustas;
		this.esPublico= false;
		this.idioma = idioma;
	}
	
	public Curso(String nombre, String descripcion) {
		this(nombre, descripcion, new LinkedList<BloqueContenido>(), 0, 0, "Español");
	}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos) {
		this(nombre, descripcion, bloques_contenidos, 0, 0, "Español");
	}
	
	
	public List<BloqueContenido> getBloquesContenidos() {
        return this.bloques_contenidos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getId() {
		return this.id;
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
	
	public String getIdioma() {
		return idioma;
	}
	
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	public void setBloquesContenidos(List<BloqueContenido> bloques_contenidos) {
		for (BloqueContenido bloque : bloques_contenidos) {
			addBloque(bloque);
		}
	}
	
	public void addBloque(BloqueContenido bloque) {
	    if (!bloques_contenidos.contains(bloque)) {
	        bloques_contenidos.add(bloque);
	    }
	}
	
	public void Publicar() {
		this.esPublico = true;
	}
	
	public long numPreguntasTotales() {
		return this.getBloquesContenidos().stream()
				.flatMap(b -> b.getPreguntas().stream())
				.count();
				
	}
	
	@Override
	public String toString() {
	    return String.format("%s - %s (%s)", nombre, idioma, autor != null ? autor.getNombre() : "Sin autor");
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    Curso curso = (Curso) o;
	    return Objects.equals(id, curso.id);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(id);
	}
}
