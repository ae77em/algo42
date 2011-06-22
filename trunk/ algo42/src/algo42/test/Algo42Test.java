package algo42.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import algo42.modelo.*;

public class Algo42Test extends TestCase {

	private Juego juego = new Juego();
	private Mision tablero;
	private Algo42 jugador;
	
	protected void setUp() {
		this.tablero = new Mision(this.juego);
		this.jugador = this.tablero.getJugador();
	}
	
	public void testAgarrarObjeto() {
		CajaArmas caja = new CajaArmas();
		int tiempo = 1;
		ArrayList<Arma> armas;
		this.tablero.ubicarObjetoEnPosicion(caja, new Punto(51, 95));
		for (int i = 1; i <= tiempo; i++) {
			this.jugador.volar(new Arriba());
		}
		armas = this.jugador.getArmas();
		assertTrue(armas.size() == 3);
	}
	
	public void testChocarANave() {
		Nave nave = new Avioneta();
		this.tablero.ubicarObjetoEnPosicion(nave, new Punto(51, 2));
		int tiempo = 100;
		for (int i = 1; i <= tiempo; i++) {
			this.jugador.volar(new Arriba());
		}
		int energia = this.jugador.getEnergia();
		assertTrue(energia == 1000 - 150);
	}
	
	public void testDestruirPierdo() {
		this.jugador.destruir();
		assertTrue(this.juego.getGanaste() == -1);
	}
	
	public void testDispararANave() {
		Nave nave = new Avioneta();
		this.tablero.ubicarObjetoEnPosicion(nave, new Punto(51, 2));
		this.jugador.disparar();
		int tiempo = 15;
		Bala bala = (Bala) tablero.getObjetoEnPosicion(new Punto(51, 97));
		for (int i = 1; i <= tiempo; i++) {
			bala.mover();
		}
		int energia = (int) nave.getEnergia();
		assertTrue(energia == 140);
	}
	
	public void testRecibirDisparo() {
		BalaLaser bala = new BalaLaser(2);
		bala.setDireccion(new Abajo());
		
		//Activo la bala manualmente
		Punto posicion = new Punto(51, 97);
		bala.activar(this.tablero, posicion);
		bala.mover();
		int energia = jugador.getEnergia();
		assertTrue(energia == 1000 - 10);
	}
	
	public void testVolarHaciaAbajoNoVuela() {
		this.jugador.volar(new Abajo());
		Punto posicionDelJugador = this.tablero.getPosicionDelJugador();
		assertTrue(posicionDelJugador.equals(new Punto(51, 99)));
	}
	
	public void testVolarHaciaArribaVuela() {
		this.jugador.volar(new Arriba());
		Punto posicionDelJugador = this.tablero.getPosicionDelJugador();
		assertTrue(posicionDelJugador.equals(new Punto(51, 97)));
	}
	
	public void testVolarHaciaDerechaVuela() {
		this.jugador.volar(new Derecha());
		Punto posicionDelJugador = this.tablero.getPosicionDelJugador();
		assertTrue(posicionDelJugador.equals(new Punto(53, 99)));
	}
	
	public void testVolarHaciaIzquierdaVuela() {
		this.jugador.volar(new Izquierda());
		Punto posicionDelJugador = this.tablero.getPosicionDelJugador();
		assertTrue(posicionDelJugador.equals(new Punto(49, 99)));
	}
}

