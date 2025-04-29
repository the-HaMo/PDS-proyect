package Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "progreso_bloques")
public class ProgresoBloque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Curso curso;

    @ManyToOne
    private BloqueContenido bloque;

    public ProgresoBloque() {}

    public ProgresoBloque(Estudiante estudiante, Curso curso, BloqueContenido bloque) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.bloque = bloque;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public BloqueContenido getBloque() {
        return bloque;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setBloque(BloqueContenido bloque) {
        this.bloque = bloque;
    }
}
