package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;

public class CivilTest extends TestCase {

	private Juego juego;
	private Mision tablero;
	private Nave nave;
	
	public void setUp() {
		this.juego = new Juego();
		this.tablero = new Mision(this.juego);
		this.nave = new Civil();
		this.nave.activar(this.tablero, new Punto(51*5, 2*5));
	}
	
	public void testActuar() {
		int tiempo = 1;
		int posInicialX = this.nave.getPosicion().getX();
		int posInicialY = this.nave.getPosicion().getY();
		for (int i = 1; i <= tiempo; i++) {
			this.nave.actuar();
		}
		assertTrue(this.nave.getPosicion().getX() == posInicialX + 1);
		assertTrue(this.nave.getPosicion().getY() == posInicialY);
	}
	
	public void testDestruirAumentaPuntaje() {
		this.nave.destruir();
		assertTrue(this.tablero.getPuntajeDelJugador() == -300);
	}
}
