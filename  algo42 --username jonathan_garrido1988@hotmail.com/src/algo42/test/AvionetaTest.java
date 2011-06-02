package algo42.test;

import algo42.modelo.*;
import junit.framework.TestCase;

public class AvionetaTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Nave nave = new Avioneta();
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
		this.nave.activar(this.tablero, new Punto(51, 2));
	}
	
	public void testActuar() {
		int tiempo = 1;
		for (int i = 1; i <= tiempo; i++) {
			this.nave.actuar();
		}
		assertTrue(this.nave.getPosicion().getY() == 5);
	}
	
	public void testDestruirAumentaPuntaje() {
		this.nave.destruir();
		assertTrue(this.tablero.getPuntajeDelJugador() == 20);
	}
}
