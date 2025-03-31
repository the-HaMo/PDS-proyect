package Clases;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueContenido {

    @JsonProperty("nombreBloque")
    private String nombreBloque;

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
}
