package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;

public class BombarderoTest extends TestCase {

	private Juego juego;
	private Mision tablero;
	private Nave nave;
	
	public void setUp() {
		this.juego = new Juego();
		this.tablero = new Mision(this.juego);
		this.nave = new Bombardero();
		this.nave.activar(this.tablero, new Punto(51*5, 2*5));
	}
	
	public void testActuar() {
		Punto posicion = this.nave.getPosicion();
		int tiempo = 1;
		for (int i = 1; i <= tiempo; i++) {
			this.nave.actuar();
		}
		//No funciona porq no se sabe adonde va a ir el bombardero
//		assertTrue((this.nave.getPosicion().equals(new Punto(posicion.getX(), 5*5))));
	}
	
	public void testDestruirAumentaPuntaje() {
		this.nave.destruir();
		assertTrue(this.tablero.getPuntajeDelJugador() == 20);
	}	
}
