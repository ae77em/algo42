package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;
import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class LaserTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Arma laser;
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
	}
	
	public void testDispararDispara() {
		try {
			this.laser = new Laser(-1,1);
		} catch (CantidadDeBalasIncorrecta e) {
			e.printStackTrace();
		}
		this.laser.disparar(new Punto(51, 99), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51, 97));
		assertTrue(objeto.getClass().getName() == "BalaLaser");
	}
	
}
