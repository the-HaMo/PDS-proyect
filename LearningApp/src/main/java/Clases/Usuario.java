package Clases;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private String contraseña;
	//private String rol;

	public Usuario() {
	}

	public Usuario(String nombre, String contraseña) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		//this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	//public String getRol() {
	//	return rol;
	//}
	
	public Integer getId() {
		return id;
	}
}
