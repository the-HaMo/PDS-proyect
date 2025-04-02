package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "Colaboradores")
@DiscriminatorValue("COLABORADOR")
public class Colaborador extends Usuario {


	
	public Colaborador() {}
	
	public Colaborador(String nombre, String contraseña) {
		super(nombre, contraseña);
		//this.rol = rol;
	}


}
