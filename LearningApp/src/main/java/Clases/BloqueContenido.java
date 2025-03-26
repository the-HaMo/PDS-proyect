package Clases;

import java.util.List;

public class BloqueContenido {

	private String nombreBloque;
	private List<Pregunta> preguntas;
	
	public BloqueContenido(String nombreBloque, List<Pregunta> preguntas) {
		this.nombreBloque = nombreBloque;
		this.preguntas = preguntas;
	}

	public String getNombreBloque() {
		return nombreBloque;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	
}
