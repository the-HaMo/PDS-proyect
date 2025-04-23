package Modelo;

import java.util.Collections;
import java.util.LinkedList;

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
	@JsonProperty("idioma")
	private String idioma;
	
	@ManyToOne
	@JoinColumn(name = "autor_id", nullable = false)
	private Colaborador autor;
	
	private int NumDescargas;
	private int NumMeGustas;
	
	// HAY VERLO 
	private Estrategia estrategia;
	
	private boolean esPublico;
	
	@JsonProperty("bloques_contenidos")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id")
    private List<BloqueContenido> bloques_contenidos;
	
	public Curso() {
		this.bloques_contenidos = new LinkedList<BloqueContenido>();
	}
	
	
public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos, int NumDescargas, int NumMeGustas, String idioma, Estrategia estrategia) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.bloques_contenidos = bloques_contenidos;
		this.NumDescargas = NumDescargas;
		this.NumMeGustas = NumMeGustas;
		this.idioma = idioma;
		this.estrategia = estrategia;
	}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos, int NumDescargas, int NumMeGustas, String idioma) {
		this(nombre, descripcion, bloques_contenidos, NumDescargas, NumMeGustas, idioma, Estrategia.ALEATORIA);
	}
	
	public Curso(String nombre, String descripcion) {
		this(nombre, descripcion, new LinkedList<BloqueContenido>(), 0, 0, "Español", Estrategia.ALEATORIA);
	}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos) {
		this(nombre, descripcion, bloques_contenidos, 0, 0, "Español", Estrategia.ALEATORIA);
	}
	
	public List<BloqueContenido> getBloquesContenidos() {
        return this.bloques_contenidos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	
	public Estrategia getEstrategia() {
		return estrategia;
	}

	public boolean isEstrategia() {
		return this.getEstrategia() != null;
	}
	
	
	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
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
	
	public boolean esPublico() {
		return this.esPublico;
	}
	
	@Override
	public String toString() {
	    return String.format("%s - %s (%s)", nombre, idioma, autor != null ? autor.getNombre() : "Sin autor");
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Curso other = (Curso) obj;
	    return id != null && id.equals(other.id);
	}

	@Override
	public int hashCode() {
	    return id != null ? id.hashCode() : 0;
	}
	
	
	public List<BloqueContenido> aplicarEstrategias() {
		List<BloqueContenido> bloques = new LinkedList<>(this.bloques_contenidos);

	    if (this.estrategia == null) {
	        throw new IllegalStateException("No se ha definido una estrategia para este curso.");
	    }

	    switch (this.estrategia) {
	        case ALEATORIA:
	            Collections.shuffle(bloques);
	            for (BloqueContenido bloque : bloques) {
	                Collections.shuffle(bloque.getPreguntas());
	            }
	            break;

	        case SECUENCIAL:
	            break;

	        case REPETICION_ESPACIADA://FALTA HACERLA
	            //bloques = aplicarRepeticionEspaciada(bloques);
	            break;

	        default:
	            throw new IllegalArgumentException("Estrategia no válida: " + this.estrategia);
	    }

	    return bloques;
	}
	
	private List<BloqueContenido> aplicarRepeticionEspaciada(List<BloqueContenido> bloques) {
	    List<BloqueContenido> bloquesConRepeticion = new LinkedList<>();

	    for (BloqueContenido bloque : bloques) {
	        List<Pregunta> preguntas = new LinkedList<>(bloque.getPreguntas());
	        List<Pregunta> falladas = new LinkedList<>();

	        for (Pregunta pregunta : preguntas) {
	            if (preguntaEsIncorrecta(pregunta)) {
	                falladas.add(pregunta);
	            }
	        }

	        // Añadir preguntas falladas al final del bloque
	        preguntas.addAll(falladas);
	        bloque.setPreguntas(preguntas);
	        bloquesConRepeticion.add(bloque);
	    }

	    return bloquesConRepeticion;
	}
	
	private boolean preguntaEsIncorrecta(Pregunta pregunta) {
	    // Implementar la lógica para determinar si una pregunta fue respondida incorrectamente
	    return false; 
	}
	
}
