package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;
import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoSimpleTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Arma torpedoSimple;
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
	}
	
	public void testDispararDispara() {
		try {
			this.torpedoSimple = new TorpedoSimple(1,1);
		} catch (CantidadDeBalasIncorrecta e) {

		}
		this.torpedoSimple.disparar(new Punto(51*5, 95*5), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51*5, 95*5 - 2));
		assertTrue(objeto != null);
	}
	
	public void testDispararNoDispara() {
		try {
			this.torpedoSimple = new Cohete(0,1);
		} catch (CantidadDeBalasIncorrecta e) {

		}
		this.torpedoSimple.disparar(new Punto(51*5, 99*5), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51*5, 97*5));
		assertTrue(objeto == null);
	}
	
}
