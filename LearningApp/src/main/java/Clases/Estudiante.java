package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "Estudiantes")
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{

	private String rol;

	public Estudiante() {}
	
	public Estudiante(String nombre, String contraseña, String rol) {
        super(nombre, contraseña);
        this.rol = rol;
    }

}
