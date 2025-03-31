package Clases;

import java.util.List;

public class PreguntaTest extends Pregunta {
    private List<String> opciones;
    private String respuestaCorrecta;

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

    public List<String> getOpciones() {
        return opciones;
    }
}