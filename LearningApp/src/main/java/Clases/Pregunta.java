package Clases;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

public abstract class Pregunta {
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
}
