package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Nave implements Movible {
        
    protected Mision tablero;
	protected Estrategia estrategia;
    protected Punto posicion;
    protected Direccion direccion;
    protected int tamanio;
	protected int energia;
	protected int puntaje;
	protected int velocidad;
	protected int equipo;
	protected int danio;
    protected boolean activo;
	protected boolean expansible;
    
    public Nave() {
    	this.activo = false;
    	this.equipo = 2;
    	this.expansible = false;
    	this.tamanio = 1;
    	this.posicion = new Punto(0, 0);
    	this.direccion = null;
    }
    
    public abstract void activar(Mision tablero, Punto posicion);

    public void actuar() {
    	if (this.activo == true) {
    		this.estrategia.usar(this, this.tablero);
    	}
    }
    
	public abstract void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta;
    
    public void disminuirEnergia(int danio) {
    	if ((this.energia - danio) <= 0){
    		this.destruir();
        } else {
        	this.energia = this.energia - danio;
        }
    }

    public void destruir() {
		this.energia = 0;
		this.activo = false;
		this.tablero.aumentarPuntaje(this.puntaje);
	}
    
    public void huir() {
		this.activo = false;
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

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}

