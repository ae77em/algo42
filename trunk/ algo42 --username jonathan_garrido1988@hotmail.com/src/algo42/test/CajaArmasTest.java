package algo42.test;

import algo42.modelo.*;
import junit.framework.TestCase;

public class CajaArmasTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Objeto caja = new CajaArmas();
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
	}
	
	public void testConsumir() {
		Algo42 jugador = this.tablero.getJugador();
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(51, 95));
		this.tablero.ubicarNaveDelJugador();
		int tiempo = 1;
		for (int i = 1; i <= tiempo; i++) {
			jugador.volar(1);
		}
		assertTrue(this.caja.getActivo() == false);
	}
	
	public void testMoverDesaparece() {
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(51, 99));
		this.caja.mover();
		assertTrue(this.caja.getActivo() == false);
	}
	
	public void testMoverMueve() {
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(51, 95));
		this.caja.mover();
		assertTrue(this.caja.getPosicion().equals(new Punto(51, 96)));
	}
}
