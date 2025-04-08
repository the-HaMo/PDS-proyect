package Clases;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "Bloques_Contenidos")
public class BloqueContenido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
	
    @JsonProperty("nombreBloque")
    private String nombreBloque;
    
    @OneToMany(mappedBy = "bloqueContenido", cascade = CascadeType.ALL)
    private List<Pregunta> preguntas;

    public BloqueContenido() {
    	this.preguntas = new LinkedList<Pregunta>();
    }

    public BloqueContenido(String nombreBloque, List<Pregunta> preguntas) {
        this.nombreBloque = nombreBloque;
        this.preguntas = preguntas;
    }
    
    public BloqueContenido(String nombreBloque) {
    	this(nombreBloque, new LinkedList<Pregunta>());
    }

    public String getNombreBloque() {
        return nombreBloque;
    }

    public void setNombreBloque(String nombreBloque) {
        this.nombreBloque = nombreBloque;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void addPregunta(Pregunta pregunta) {
        if (!preguntas.contains(pregunta)) {
            preguntas.add(pregunta);
            pregunta.setBloqueContenido(this);
        }
    }
    
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
    
    public String toString() {
    	       return ("Bloque de contenido: " + this.nombreBloque + "\nPreguntas: " + this.preguntas.toString());
    }
}
