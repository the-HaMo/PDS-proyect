package Modelo;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(curso, id, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		return Objects.equals(curso, other.curso) && Objects.equals(id, other.id)
				&& Objects.equals(usuario, other.usuario);
	}

	

	
}
