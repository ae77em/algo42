package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;
import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class CoheteTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Arma cohete;
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
	}
	
	public void testDispararDispara() {
		try {
			this.cohete = new Cohete(1,1);
		} catch (CantidadDeBalasIncorrecta e) {
			e.printStackTrace();
		}
		this.cohete.disparar(new Punto(51, 99), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51, 97));
		assertTrue(objeto != null);
	}
	
	public void testDispararNoDispara() {
		try {
			//No le paso ninguna cantidad de balas
			this.cohete = new Cohete(0,1);
		} catch (CantidadDeBalasIncorrecta e) {

		}
		this.cohete.disparar(new Punto(51, 99), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51, 97));
		assertTrue(objeto == null);
	}
}
