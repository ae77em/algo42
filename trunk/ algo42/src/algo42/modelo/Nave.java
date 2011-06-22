package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Nave implements Movible {
        
    private Mision tablero;
    private Estrategia estrategia;
    private Punto posicion;
    private Direccion direccion;
    private int tamanio, energia, puntaje, velocidad, equipo, danio;
    private boolean activo, expansible;
    
    public Nave(Estrategia estrategia, int velocidad, int energia, int danio, int puntaje) {
    	this.activo = false;
    	this.equipo = 2;
    	this.expansible = false;
    	this.tamanio = 1;
    	this.posicion = new Punto(0, 0);
    	this.direccion = null;
    	this.estrategia = new IdaVuelta();
        this.estrategia = estrategia;
        this.velocidad = velocidad;
        this.energia = energia;
        this.danio = danio;
        this.puntaje = puntaje;
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

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}
	
	public void setExpansible(boolean expansible) {
		this.expansible = expansible;
	}
	
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	public void setDanio(int danio) {
		this.danio = danio;
	}
	
	public void setEquipo(int equipo) {
		this.equipo = equipo;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public void setActivo(boolean valor) {
		this.activo = valor;
	}
	
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	public void setTablero(Mision tablero) {
		this.tablero = tablero;
	}
	
	public Mision getTablero() {
		return this.tablero;
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
	public Estrategia getEstrategia() {
		return this.estrategia;
	}
	
	public void mover() {
		this.actuar();
	}
	
	public int getX() {
		return this.posicion.getX();
	}

	public int getY() {
		return this.posicion.getY();
	}
	
	public void vivir() {
		this.mover();
	}
}