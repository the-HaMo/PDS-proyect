package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Usuario{

	public Estudiante(String nombre, String contraseña, String rol) {
        super(nombre, contraseña, rol);
    }

}
