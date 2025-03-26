package Clases;

import java.util.List;

public class Curso {

	private String nombre;
	private String descripcion;
	private List<BloqueContenido> bloques_contenidos;
	
	public Curso(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
}
