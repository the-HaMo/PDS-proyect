package Clases;

public abstract class Usuario {

	private String nombre;
	private String contraseña;
	private String rol;
	private String ID;
	
	public Usuario(String nombre, String contraseña, String rol) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getRol() {
		return rol;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return ID;
	}
}
