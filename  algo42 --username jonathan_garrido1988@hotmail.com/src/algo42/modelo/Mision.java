package algo42.modelo;

import java.util.*;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Mision {
	
	private boolean activa;
	private ArrayList<Movible> espacioAereo;
	private Flota flota;
	private Juego juego;
	private Algo42 jugador;
	private ArrayList<Movible> navesNoEnemigas;
	private int numeroDeMision;
	private int puntajeJugador;
	private Punto puntoInicialJugador;

	public boolean getActivo(){
		return this.activa;
	}
	
	public Algo42 getJugador(){
		return this.jugador;
	}
	
	public int getPuntajeDelJugador(){
		return this.puntajeJugador;
	}
	
	public Punto getPosicionDelJugador(){
		return this.jugador.getPosicion();
	}
	
	public Movible getObjetoEnPosicion(Punto posicion){
		for (int i = 0; i < (this.espacioAereo.size()); i ++){
			if (this.espacioAereo.get(i).getPosicion().equals(posicion)){
				return this.espacioAereo.get(i);
			}
		}
		return null;
	}
	
	public void aumentarPuntaje (int puntaje){
		this.puntajeJugador = this.puntajeJugador + puntaje;
	}
	
	public void borrarObjetoEnPosicion (Punto posicion){
		for (int i = 0; i < (this.espacioAereo.size()); i ++){
			if (this.espacioAereo.get(i).getPosicion().equals(posicion)){
				this.espacioAereo.remove(i);
			}
		}
	}
	
	public void comenzar() throws CantidadDeBalasIncorrecta {
		
		ArrayList<Movible> navesEnemigas, navesCiviles;
		Nave naveActual;
		
		this.activa = true;		
		while (this.puntajeJugador < 1000){
			navesEnemigas = this.flota.getNaves();
			navesCiviles = this.crearNavesNoEnemigas();
			int i = 0;
			while (i < navesEnemigas.size()){
				naveActual = (Nave) navesEnemigas.remove(0);
				if (i < navesCiviles.size()){
					naveActual = (Nave) navesCiviles.remove(0);
				}
				naveActual.activar(this, this.flota.getPosicionNave(i + 1));/*Le pedis la posicion de la nave a la flota*/
				naveActual.actuar();
				i ++;
			}
			this.activa = false;
			this.misionCompletada();
		}
	}
	
	public void guiaDestruida() throws CantidadDeBalasIncorrecta{
		this.misionCompletada();
	}
	
	public Movible hayAlguien(Punto posicion) throws CoordenadaFueraDeRangoError{
		Movible casillero = null;
		
		if((posicion.getX() <= 1) || (posicion.getX() >= 100)){
			throw new CoordenadaFueraDeRangoError();
		}
		else if ((posicion.getY() <= 1) || (posicion.getY() >= 100)){
			throw new CoordenadaFueraDeRangoError();
		}
		else {			
			for (int i = 0; i < (this.espacioAereo.size()); i ++){
				if (this.espacioAereo.get(i).getPosicion().distancia(posicion) <= (this.espacioAereo.get(i).getTamanio() + this.getObjetoEnPosicion(posicion).getTamanio())){
					casillero = this.espacioAereo.get(i);
				}
			}
		}
		return casillero;
	}

	public void inicializarMisionEnJuego(Integer contadorDeMision, Juego unJuego){
		
		this.juego = unJuego;
		this.numeroDeMision = contadorDeMision;
		this.puntoInicialJugador = new Punto(51,99);
		this.puntajeJugador = 0;
		this.espacioAereo = new ArrayList<Movible>();
		this.jugador = new Algo42();
		this.flota = new Flota();
		
		this.ubicarNaveDelJugador();
	}
	
	public void misionPerdida(){
		this.activa = false;
		this.juego.perdiste();
	}
	
	public void setActivo(boolean valor){
		this.activa = valor;
	}
	
	public void ubicarBalaEnPosicion(Bala bala, Punto posicion) {
		
		ResolvedorDeChoque resolvedorDeChoque;
		TipoDeChoque tipoDeChoque;
		Movible objeto = null;
		try {
			objeto = this.hayAlguien(posicion);
		} catch (CoordenadaFueraDeRangoError e) {
			e.printStackTrace();
		}
		
		bala.activar(this, posicion);
		if (objeto == null){
			this.espacioAereo.add(0, bala);
		}
		else{
			resolvedorDeChoque = new ResolvedorDeChoque();
			tipoDeChoque = resolvedorDeChoque.resolver(bala, objeto);
			tipoDeChoque.chocarEntre(bala, objeto);
		}
	}
	
	private ArrayList<Movible> crearNavesNoEnemigas() {
		this.navesNoEnemigas = new ArrayList<Movible>();
		int i = 0;
		while (i <= this.numeroDeMision){
			this.navesNoEnemigas.add(0, new Civil());
			this.navesNoEnemigas.add(0, new Helicoptero());
		}
		return this.navesNoEnemigas;
	}
	
	private void misionCompletada() throws CantidadDeBalasIncorrecta {
		this.activa = false;
		this.juego.aumentarContadorDeMision();
		this.juego.comenzar();
	}
	
	public void ubicarNaveDelJugador(){
		this.jugador.setPosicion(this.puntoInicialJugador);
		this.espacioAereo.add(0, this.jugador);
		this.jugador.activar(this, this.puntoInicialJugador);
	}
	
	public void ubicarObjetoEnPosicion(Movible objeto, Punto posicion){
		objeto.setPosicion(posicion);
		this.espacioAereo.add(0, objeto);
		objeto.activar(this, posicion);
	}
}
