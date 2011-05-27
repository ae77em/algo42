package algo42.modelo;

import java.util.*;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Mision {
	
	private boolean activa;
	private ArrayList<ElementoPosicionableOcupador> espacioAereo;
	private Flota flota;
	private Juego juego;
	private Algo42 jugador;
	private ArrayList<ElementoPosicionableOcupador> navesNoEnemigas;
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
	
	public ElementoPosicionableOcupador getObjetoEnPosicion(Punto posicion){
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
	
	public void comenzar() {
		
		ArrayList<ElementoPosicionableOcupador> navesEnemigas, navesCiviles;
		Nave naveActual;
		
		this.activa = true;
		navesEnemigas = this.flota.getNaves();
		navesCiviles = this.navesNoEnemigas;
		
		int i = 0;
		while (i < navesEnemigas.size()){
			naveActual = (Nave) navesEnemigas.remove(0);
			if (i < navesCiviles.size()){
				naveActual = (Nave) navesCiviles.remove(0);
			}
			naveActual.activar(this);
			naveActual.actuar();
			i ++;
		}
		if(this.puntajeJugador >= 1000){
			this.activa = false;
			this.misionCompletada();
		}
	}
	
	public void guiaDestruida(){
		this.misionCompletada();
	}

	public ElementoPosicionableOcupador hayAlguien(Punto posicion) throws CoordenadaFueraDeRangoError{
		ElementoPosicionableOcupador casillero = null;
		
		if((posicion.getX() <= 1) || (posicion.getX() >= 100)){
			throw new CoordenadaFueraDeRangoError();
		}
		else if ((posicion.getY() <= 1) || (posicion.getY() >= 100)){
			throw new CoordenadaFueraDeRangoError();
		}
		else {			
			for (int i = 0; i < (this.espacioAereo.size()); i ++){
				if (this.espacioAereo.get(i).getPosicion().distancia(posicion) <= (this.espacioAereo.get(i).getRadio() + this.getObjetoEnPosicion(posicion).getRadio())){
					casillero = this.espacioAereo.get(i);
				}
			}
			if (casillero.getOcupado() == false){
				return null;
			}
			else {
				return casillero;
			}
		}
	}

	public void inicializarMisionEnJuego(Integer contadorDeMision, Juego unJuego) {
		
		this.juego = unJuego;
		this.numeroDeMision = contadorDeMision;
		this.puntoInicialJugador = new Punto(51,99);
		this.puntajeJugador = 0;
		this.espacioAereo = new ArrayList<ElementoPosicionableOcupador>();
		this.jugador = new Algo42();
		this.flota = new Flota();
		this.flota.inicializarFlota(this.numeroDeMision);
		
		this.crearNavesNoEnemigas();
		this.ubicarNaveDelJugador();
	}
	
	public void misionPerdida(){
		this.activa = false;
		this.juego.perdiste();
	}
	
	public void setActivo(boolean valor){
		this.activa = valor;
	}
	
	public void ubicarBalaEnPosicion(Bala bala, Punto posicion) throws CoordenadaFueraDeRangoError{
		
		ResolvedorDeChoque resolvedorDeChoque;
		TipoDeChoque tipoDeChoque;
		ElementoPosicionableOcupador objeto = this.hayAlguien(posicion);
		
		if (objeto == null){
			bala.setPosicion(posicion);
			this.espacioAereo.add(0, bala);
		}
		else{
			resolvedorDeChoque = new ResolvedorDeChoque();
			tipoDeChoque = resolvedorDeChoque.resolver(bala, objeto);
			tipoDeChoque.chocarEntre(bala, objeto);
		}
	}
	
	private void crearNavesNoEnemigas() {
		this.navesNoEnemigas = new ArrayList<ElementoPosicionableOcupador>();
		int i = 0;
		while (i <= this.numeroDeMision){
			this.navesNoEnemigas.add(0, new Civil());
			this.navesNoEnemigas.add(0, new Helicoptero());
		}
	}
	
	private void misionCompletada() {
		this.activa = false;
		this.juego.aumentarContadorDeMision();
		this.juego.comenzar();
	}
	
	private void ubicarNaveDelJugador(){
		this.jugador.setPosicion(this.puntoInicialJugador);
		this.espacioAereo.add(0, this.jugador);
		this.jugador.activarMisionEnPosicion(this, this.puntoInicialJugador);
	}
	
	public void ubicarObjetoEnPosicion(ElementoPosicionableOcupador objeto, Punto posicion){
		objeto.setPosicion(posicion);
		this.espacioAereo.add(0, objeto);
		objeto.activarMisionEnPosicion(this, posicion);
	}
}
