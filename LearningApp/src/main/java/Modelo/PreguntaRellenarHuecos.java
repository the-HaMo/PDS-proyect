package Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

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
    
    public boolean isCorrecta(String respuestaUsuario) {
        if (respuestaUsuario == null || respuesta == null) return false;
        return respuestaUsuario.trim().equalsIgnoreCase(respuesta.trim());
    }


    public String getTextoConHuecos() {
        return textoConHuecos;
    }
    
	public String toString() {
		return ("Pregunta de rellenar huecos: " + this.enunciado + "\nTexto con huecos: "
				+ this.textoConHuecos + "\nRespuesta: " + this.respuesta);
	}
	@Override
	public String getTipo() {
	    return "Rellenar Huecos";
	}

}
