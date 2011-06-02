package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;

public class CazaTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Nave nave = new Caza();
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
		this.nave.activar(this.tablero, new Punto(51, 2));
	}
	
	public void testActuar() {
		int tiempo = 1;
		for (int i = 1; i <= tiempo; i++) {
			this.nave.actuar();
		}
		assertTrue(this.nave.getPosicion().getY() == 4);
	}
	
	public void testDestruirAumentaPuntaje() {
		this.nave.destruir();
		assertTrue(this.tablero.getPuntajeDelJugador() == 30);
	}
}
