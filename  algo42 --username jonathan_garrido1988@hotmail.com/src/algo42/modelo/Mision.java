package algo42.modelo;

import java.util.*;
import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Mision {
        
	private boolean activa;
    private ArrayList<Movible> espacioAereo;
    private Flota flota;
    private Juego juego;
    private Algo42 jugador;
    private int puntajeJugador;
    private Punto puntoInicialJugador;
    
    public Mision (Juego juego) {
    	this.juego = juego;
    	this.puntoInicialJugador = new Punto(51,99);
    	this.puntajeJugador = 0;
    	this.espacioAereo = new ArrayList<Movible>();
    	this.jugador = new Algo42();
    	this.activa = true;
    	this.ubicarNaveDelJugador();
    }
    
    public void comenzar() {
    	ArrayList<Movible> navesEnemigas;
    	Nave naveActual;
    	
    	this.flota = new Flota();	/*Crear flotas cada "N" segundos*/
    	this.crearNaveNoEnemiga();	/*Crear NaveNoEnemiga cada "N" segundos*/
    	navesEnemigas = this.flota.getNaves();
    	if (this.puntajeJugador < 1000) {
    		for (int i = 0; i < 5; i++) {
    			naveActual = (Nave) navesEnemigas.get(i);
    			this.espacioAereo.add(naveActual);
    			naveActual.activar(this, this.flota.getPosicionNave(i));
    			naveActual.actuar();
    		}
    	} else {
    		this.activa = false;
    		this.misionCompletada();
    	}
    }
    
    private void crearNaveNoEnemiga() {
    	int numeroAlAzar = (int) (Math.random()*2+1);
    	int otroNumeroAlAzar;
    	Nave naveNoEnemiga;
    	if (numeroAlAzar == 1) {
    		naveNoEnemiga = new Civil();
    		otroNumeroAlAzar = (int) (Math.random()*99+1);
    		while (otroNumeroAlAzar == 1) {
    			otroNumeroAlAzar = (int) (Math.random()*99+1);
    		}
    		naveNoEnemiga.activar(this, new Punto(2, otroNumeroAlAzar));
    	} else {
    		naveNoEnemiga = new Helicoptero();
    		naveNoEnemiga.activar(this, new Punto(51, 2));
    	}
    	this.espacioAereo.add(naveNoEnemiga);
    	naveNoEnemiga.actuar();
    }
    
    public void aumentarPuntaje (int puntaje) {
    	this.puntajeJugador = this.puntajeJugador + puntaje;
    }
    
    public Movible hayAlguien(Punto posicion) throws CoordenadaFueraDeRangoError {
    	Movible casillero = null;
    	
    	if((posicion.getX() <= 1) || (posicion.getX() >= 100) || (posicion.getY() <= 1) || (posicion.getY() >= 100)) {
    		throw new CoordenadaFueraDeRangoError();
    	} else {                  
    		for (int i = 0; i < (this.espacioAereo.size()); i ++) {
    			if (this.espacioAereo.get(i).getPosicion().distancia(posicion) <= this.espacioAereo.get(i).getTamanio()) {
    				casillero = this.espacioAereo.get(i);
    			}
    		}
    	}
    	return casillero;
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
    	if (objeto == null) {
    		this.espacioAereo.add(0, bala);
    	} else {
    		resolvedorDeChoque = new ResolvedorDeChoque();
    		tipoDeChoque = resolvedorDeChoque.resolver(bala, objeto);
    		tipoDeChoque.chocarEntre(bala, objeto);
    	}
    }
    
    public void ubicarNaveDelJugador() {
    	this.jugador.setPosicion(this.puntoInicialJugador);
    	this.espacioAereo.add(0, this.jugador);
    	this.jugador.activar(this, this.puntoInicialJugador);
    }
    
    public void ubicarObjetoEnPosicion(Movible objeto, Punto posicion) {
    	objeto.setPosicion(posicion);
    	this.espacioAereo.add(0, objeto);
    	objeto.activar(this, posicion);
    }
    
    public Movible getObjetoEnPosicion(Punto posicion) {
    	for (int i = 0; i < (this.espacioAereo.size()); i++) {
    		if (this.espacioAereo.get(i).getPosicion().equals(posicion)){
    			return this.espacioAereo.get(i);
    		}
    	}
    	return null;
    }
    
    private void misionCompletada() {
    	this.activa = false;
    	this.juego.ganaste();
    }
    
    public void misionPerdida(){
    	this.activa = false;
    	this.juego.perdiste();
    }
    
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
}	