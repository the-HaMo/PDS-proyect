package Clases;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ESTUDIANTE")
public class Estudiante extends Usuario{


	public Estudiante() {}
	
	public Estudiante(String nombre, String contraseña) {
        super(nombre, contraseña);
        //this.rol = rol;
    }

}
