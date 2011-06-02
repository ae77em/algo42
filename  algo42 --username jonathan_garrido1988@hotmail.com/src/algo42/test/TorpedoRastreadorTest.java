package algo42.test;

import junit.framework.TestCase;
import algo42.modelo.*;
import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoRastreadorTest extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Arma torpedoRastreador;
	
	public void setUp() {
		this.tablero = new Mision(this.juego);
	}
	
	public void testDispararDispara() {
		try {
			this.torpedoRastreador = new Cohete(1,1);
		} catch (CantidadDeBalasIncorrecta e) {
			e.printStackTrace();
		}
		this.torpedoRastreador.disparar(new Punto(51, 99), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51, 97));
		assertTrue(objeto.getClass().getName() == "BalaTorpedoRastreador");
	}
	
	public void testDispararNoDispara() {
		try {
			this.torpedoRastreador = new Cohete(0,1);
		} catch (CantidadDeBalasIncorrecta e) {
			e.printStackTrace();
		}
		this.torpedoRastreador.disparar(new Punto(51, 99), this.tablero, new Arriba());
		Movible objeto = this.tablero.getObjetoEnPosicion(new Punto(51, 97));
		assertTrue(objeto == null);
	}
	
}
