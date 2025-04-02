package Clases;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PreguntasTest")
@DiscriminatorValue("TEST")
public class PreguntaTest extends Pregunta {

    @JsonProperty("respuestaCorrecta")
    private String respuestaCorrecta;
    private List<String> opciones;

    public PreguntaTest() {}

    public PreguntaTest(String enunciado, List<String> opciones, String respuestaCorrecta) {
        super(enunciado);
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String getRespuesta() {
        return respuestaCorrecta;
    }
    
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<String> getOpciones() {
        return opciones;
    }
    
    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
    
	public String toString() {
		return ("Pregunta de test: " + this.enunciado + "\nOpciones: " + this.opciones.toString() + "\nRespuesta: "
				+ this.respuestaCorrecta);
	}
}