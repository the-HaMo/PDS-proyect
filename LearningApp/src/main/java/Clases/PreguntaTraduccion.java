package Clases;

public class PreguntaTraduccion extends Pregunta {
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
}
