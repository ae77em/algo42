package algo42.modelo;

import java.util.ArrayList;
import java.util.Iterator;

import algo42.modelo.excepciones.*;

public class Algo42 implements Movible {
	
	private ArrayList<Arma> armas = new ArrayList<Arma>();
	private int velocidad, energia, equipo, danio, tamanio;
	private boolean activo, expansible;
    private Punto posicion;
    private Direccion direccion;
    private Mision tablero;
        
    public Algo42() {
    	Laser laser = null;
        try {
        	laser = new Laser(-1, 1);
        } catch (CantidadDeBalasIncorrecta e) {
        	// Nunca se llega a tirar esta excepcion
        }
        this.armas.add(laser);
        this.velocidad = 2;
        this.energia = 1000;
        this.activo = false;
        this.equipo = 1;
        this.expansible = true;
        this.danio = 1000;
        this.direccion = null;
        this.tamanio = 1;
        this.posicion = new Punto(0, 0);
    }
        
    public void activar(Mision tablero, Punto puntoInicialJugador) {
    	this.activo = true;
        this.tablero = tablero;
        this.posicion = puntoInicialJugador;
        this.direccion = new Arriba();
    }
        
    public void volar(int direccion) {
    	if (direccion == 2) {
    		this.direccion = new Izquierda();
        } else {
        	if (direccion == 3) {
        		this.direccion = new Abajo();
        	} else {
        		if (direccion == 4) {
        			this.direccion = new Derecha();
        		}
        	}
        }
        this.direccion.trasladar(this, this.tablero);
    }
        
    public void disparar() {
    	Iterator<Arma> iterador = armas.iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.posicion, this.tablero, this.direccion);
    	} 
    }
        
    public void cargar(Arma arma) {
    	this.armas.add(arma);
    }
        
    public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
    	if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((1000 - this.energia) >= cantidadEnergia) {
                    this.energia = this.energia + cantidadEnergia;
            } else {
            	this.energia = 1000;
            }
        }
    }
    
    public void disminuirEnergia (int danio) {
    	if ((this.energia - danio) <= 0){
    		this.destruir();
        } else {
        	this.energia = this.energia - danio;
        }
    }
        
    public void destruir(){
    	this.energia = 0;
        this.activo = false;
        this.tablero.misionPerdida();
    }
        
    public Punto getPosicion() {
    	return this.posicion;
    }
        
    public void setPosicion(Punto posicion) {
    	this.posicion = posicion;
    }

    public boolean getActivo() {
    	return this.activo;
    }
        
    public ArrayList<Arma> getArmas() {
    	return this.armas;
    }
        
    public int getDanio() {
    	return this.danio;
    }
        
    public int getEnergia() {
    	return this.energia;
    }
        
    public int getEquipo() {
    	return this.equipo;
    }
        
    public boolean getExpansible() {
    	return this.expansible;
    }
        
    public int getVelocidad() {
    	return this.velocidad;
    }
        
    public int getTamanio() {
    	return this.tamanio;
    }
}