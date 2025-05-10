package Modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class EstadisticaUsuarioTest {

	private Estudiante estudiante;
	private EstadisticaUsuario stats;
	
	
	@BeforeEach
	public void setUp() {
		estudiante = new Estudiante("jorge", "jorge1234");
		stats = estudiante.getStats();
	}
	
	@Test
	public void testMejorRacha() {
		stats.respuestaCorrecta();
		stats.respuestaCorrecta();
		stats.respuestaCorrecta();
		
		assertEquals(stats.getMejorRacha(), 3);
	}

	@Test
	public void testRacha() {
		stats.respuestaCorrecta();
		stats.respuestaCorrecta();
		stats.respuestaIncorrecta();
		
		assertEquals(stats.getRacha(), 0);
	}
	
	@Test
    public void testTiempoUso() throws InterruptedException {
        stats.iniciarTiempo();
        Thread.sleep(1001); //simula un segundo
        stats.finalizarTiempo();

        assertTrue(stats.getTiempoUso() > 0); //mayor que 0 segundos 
    }
	
}
