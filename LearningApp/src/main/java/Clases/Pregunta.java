package Clases;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "tipo"
)
@JsonSubTypes({
	    @JsonSubTypes.Type(value = PreguntaTest.class, name = "test"),
	    @JsonSubTypes.Type(value = PreguntaRellenarHuecos.class, name = "huecos"),
	    @JsonSubTypes.Type(value = PreguntaTraduccion.class, name = "traduccion")
})

@Entity
@Table(name = "Preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_preguntas", discriminatorType = DiscriminatorType.STRING)
public abstract class Pregunta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "bloque_contenido_id", nullable = false)
	private BloqueContenido bloqueContenido;
	
    protected String enunciado;
    
    public Pregunta() {}

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    
    public abstract String getRespuesta();

	public abstract String toString();
}
