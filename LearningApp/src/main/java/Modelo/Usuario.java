package Modelo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Like> likes;
    
    public Usuario() {
    	this.likes = new HashSet<Like>();
    }

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
    
	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}

    public Integer getId() {
        return id;
    }
    
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
    
    public Set<Like> getLikes() {
		return likes;
	}

    
    public void addLike(Like like) {
    	likes.add(like);
    }
    
	public abstract void addCurso(Curso curso);
    public abstract List<Curso> getCursos();
    public abstract void setCursos(List<Curso> cursos);
}
