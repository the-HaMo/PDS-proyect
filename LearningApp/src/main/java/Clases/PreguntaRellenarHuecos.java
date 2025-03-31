package Clases;

public class PreguntaRellenarHuecos extends Pregunta {
    private String textoConHuecos;
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

    public String getTextoConHuecos() {
        return textoConHuecos;
    }
}
