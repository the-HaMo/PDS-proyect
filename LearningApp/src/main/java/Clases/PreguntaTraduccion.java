package Clases;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
    
	public String toString() {
		return ("Pregunta de traducción: " + this.enunciado + "\nRespuesta: " + this.respuestaCorrecta);
	}
}
