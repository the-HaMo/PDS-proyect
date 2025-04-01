package Clases;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Curso {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String nombre;
	private String descripcion;
	
	@JsonProperty("bloques_contenidos")
	@OneToMany(mappedBy = "Curso", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BloqueContenido> bloques_contenidos;
	
	public Curso() {}
	
	public Curso(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Curso(String nombre, String descripcion, List<BloqueContenido> bloques_contenidos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.bloques_contenidos = bloques_contenidos;
	}
	
	public List<BloqueContenido> getBloquesContenidos() {
        return this.bloques_contenidos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setBloquesContenidos(List<BloqueContenido> bloques_contenidos) {
		this.bloques_contenidos = bloques_contenidos;
	}
	
}
