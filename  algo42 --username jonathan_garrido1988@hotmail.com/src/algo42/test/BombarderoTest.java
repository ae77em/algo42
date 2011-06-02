package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;

public class BombarderoTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Nave nave = new Bombardero();
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
		this.nave.activar(this.tablero, new Punto(51, 2));
	}
	
	public void testActuar() {
		Punto posicion = this.nave.getPosicion();
		int tiempo = 1;
		for (int i = 1; i <= tiempo; i++) {
			this.nave.actuar();
		}
		assertTrue((this.nave.getPosicion().equals(new Punto(posicion.getX(), 3))));
	}
	
	public void testDestruirAumentaPuntaje() {
		this.nave.destruir();
		assertTrue(this.tablero.getPuntajeDelJugador() == 30);
	}	
}
