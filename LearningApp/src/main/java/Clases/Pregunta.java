package Clases;

public abstract class Pregunta {
    protected String enunciado;

    public Pregunta() {}

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public abstract String getRespuesta();
}
