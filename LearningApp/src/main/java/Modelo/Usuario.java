package Modelo;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "Usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Cambio de TABLE_PER_CLASS
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String contrasena;

    public Usuario() {}

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contrasena;
    }

    public Integer getId() {
        return id;
    }
    
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
    
    public abstract void addCurso(Curso curso);
    public abstract List<Curso> getCursos();
}
