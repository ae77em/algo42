package algo42.test;

import algo42.modelo.*;
import junit.framework.TestCase;

public class AvionetaTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Nave nave = new Avioneta();
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
		this.nave.activar(this.tablero, new Punto(51*5, 2*5));
	}
	
	public void testActuar() {
		int tiempo = 1;
		int posInicialX = this.nave.getPosicion().getX();
		int posInicialY = this.nave.getPosicion().getY();
		for (int i = 1; i <= tiempo; i++) {
			this.nave.actuar();
		}
		assertTrue(this.nave.getPosicion().getY() == posInicialY + 3);
		assertTrue(this.nave.getPosicion().getX() == posInicialX);
	}
	
	public void testDestruirAumentaPuntaje() {
		this.nave.destruir();
		assertTrue(this.tablero.getPuntajeDelJugador() == 20);
	}
}
