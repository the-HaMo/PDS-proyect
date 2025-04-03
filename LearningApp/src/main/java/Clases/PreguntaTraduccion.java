package Clases;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRADUCCION")
public class PreguntaTraduccion extends Pregunta {
    
	@JsonProperty("respuestaCorrecta")
	private String respuestaCorrecta;

    public PreguntaTraduccion() {}

    public PreguntaTraduccion(String enunciado, String respuestaCorrecta) {
        super(enunciado);
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String getRespuesta() {
        return respuestaCorrecta;
    }
    
    public boolean isCorrecta(String respuesta) {
        return respuesta.equals(respuestaCorrecta);
    }
    
	public String toString() {
		return ("Pregunta de traducción: " + this.enunciado + "\nRespuesta: " + this.respuestaCorrecta);
	}
}
