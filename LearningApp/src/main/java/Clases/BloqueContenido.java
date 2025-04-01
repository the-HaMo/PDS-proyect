package Clases;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "bloques_contenidos")
public class BloqueContenido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
	
    @JsonProperty("nombreBloque")
    private String nombreBloque;
    
    @OneToMany(mappedBy = "bloqueContenido")
    private List<Pregunta> preguntas;

    public BloqueContenido() {}

    public BloqueContenido(String nombreBloque, List<Pregunta> preguntas) {
        this.nombreBloque = nombreBloque;
        this.preguntas = preguntas;
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

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    public String toString() {
    	       return ("Bloque de contenido: " + this.nombreBloque + "\nPreguntas: " + this.preguntas.toString());
    }
}
