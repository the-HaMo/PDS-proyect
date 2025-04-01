package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "colaboradores")
public class Colaborador extends Usuario {


	public Colaborador(String nombre, String contraseña, String rol) {
		super(nombre, contraseña, rol);
	}


}
