package Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="Likes", uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "curso_id"}))
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;
	
	public Like() {}
	
	public Like(Usuario usuario, Curso curso) {
		this.usuario = usuario;
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Like like = (Like) o;

	    if (!usuario.equals(like.usuario)) return false;
	    return curso.equals(like.curso);
	}

	@Override
	public int hashCode() {
	    int result = usuario.hashCode();
	    result = 31 * result + curso.hashCode();
	    return result;
	}

	
}
