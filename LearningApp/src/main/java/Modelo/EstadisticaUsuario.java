package Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class EstadisticaUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long tiempoUso;
	private long tiempoInicio;
	private int racha;
	private int mejorRacha;
	
	@OneToOne
	@JoinColumn(name = "estudiante_id")
	private Estudiante estudiante;
	
	public EstadisticaUsuario() {};
	
	public EstadisticaUsuario(Estudiante estudiante) {
		this.estudiante = estudiante;
		this.tiempoUso = 0;
		this.racha = 0;
		this.mejorRacha = 0;
		this.tiempoInicio = -1;
	}
	
	public void iniciarTiempo() {
		tiempoInicio = System.currentTimeMillis(); 
	}
	
	public void finalizarTiempo() {
		if (tiempoInicio != -1) {
			long TiempoSesion = System.currentTimeMillis() - tiempoInicio;
			tiempoUso += TiempoSesion;
			tiempoInicio = -1;
		}
	}
	
	public void respuestaCorrecta() {
		racha++;
		if (racha > mejorRacha) {
			mejorRacha = racha;
		}
	}
	
	public void respuestaIncorrecta() {
		racha = 0;
	}
	
	public long getTiempoUso() {
		return tiempoUso/1000;
	}

	public long getTiempoInicio() {
		return tiempoInicio;
	}

	public int getRacha() {
		return racha;
	}

	public int getMejorRacha() {
		return mejorRacha;
	}

	public void setTiempoUso(long tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public void setTiempoInicio(long tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public void setRacha(int racha) {
		this.racha = racha;
	}

	public void setMejorRacha(int mejorRacha) {
		this.mejorRacha = mejorRacha;
	}
	
	
}
