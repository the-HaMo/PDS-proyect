package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "Colaboradores")
@DiscriminatorValue("COLABORADOR")
public class Colaborador extends Usuario {

	private String rol;
	
	public Colaborador() {}
	
	public Colaborador(String nombre, String contraseña, String rol) {
		super(nombre, contraseña);
		this.rol = rol;
	}


}
