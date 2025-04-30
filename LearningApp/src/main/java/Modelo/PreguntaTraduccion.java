package Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
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
    	if (respuesta == null || respuestaCorrecta == null) {return false;}
        return respuesta.trim().equalsIgnoreCase(respuestaCorrecta.trim());
    }
    
	public String toString() {
		return ("Pregunta de traducción: " + this.enunciado + "\nRespuesta: " + this.respuestaCorrecta);
	}
	
	@Override
	public String getTipo() {
	    return "Traducción";
	}

}
