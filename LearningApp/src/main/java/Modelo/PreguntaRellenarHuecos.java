package Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class PreguntaRellenarHuecos extends Pregunta {
	
	@JsonProperty("textoConHuecos")
    private String textoConHuecos;
	 
	@JsonProperty("respuesta")
    private String respuesta;

    public PreguntaRellenarHuecos() {}

    public PreguntaRellenarHuecos(String enunciado, String textoConHuecos, String respuesta) {
        super(enunciado);
        this.textoConHuecos = textoConHuecos;
        this.respuesta = respuesta;
    }

    @Override
    public String getRespuesta() {
        return respuesta;
    }
    
    public boolean isCorrecta(String res) {
       return  res.toLowerCase().equals(respuesta);
    }

    public String getTextoConHuecos() {
        return textoConHuecos;
    }
    
	public String toString() {
		return ("Pregunta de rellenar huecos: " + this.enunciado + "\nTexto con huecos: "
				+ this.textoConHuecos + "\nRespuesta: " + this.respuesta);
	}
}
