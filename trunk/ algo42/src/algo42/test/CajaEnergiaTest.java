package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;

public class CajaEnergiaTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Objeto caja = new CajaEnergia();
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
	}
	
	public void testConsumir() {
		Algo42 jugador = this.tablero.getJugador();
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(51*5, 98*5));
		int tiempo = 1;
		for (int i = 1; i <= tiempo; i++) {
			jugador.volar(new Arriba());
		}
		assertTrue(this.caja.getActivo() == false);
	}
	
	public void testMoverDesaparece() {
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(51*5, 99*5));
		this.caja.mover();
		assertTrue(this.caja.getActivo() == false);
	}
	
	public void testMoverMueve() {
		int posInicialX = 51*5;
		int posInicialY = 95*5;
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(posInicialX, posInicialY));
		this.caja.mover();
		assertTrue(this.caja.getPosicion().equals(new Punto(posInicialX, posInicialY + 1)));
	}
}
