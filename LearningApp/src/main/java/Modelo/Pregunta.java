package Modelo;

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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pregunta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	public abstract String getTipo();  // nuevo método abstracto

	@Enumerated(EnumType.STRING)
	private Dificultad dificultad;
	
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
    
    public Integer getId() {
        return id;
    }
    
    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }
 
    
    public abstract String getRespuesta();

	public abstract String toString();
}
