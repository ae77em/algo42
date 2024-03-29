package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Bala implements Movible {

	private int velocidad, danio, equipo, energia, tamanio;
	private Mision tablero;
	private Punto posicion;
	private boolean activo, expansible;
	private Direccion direccion;
	
	public Bala (int equipo, int unaVelocidad, int unDanio){
		this.velocidad = unaVelocidad;
		this.danio = unDanio;
		this.energia = 1;
		this.tamanio = 0;
		this.activo = false;
		this.expansible = false;
		this.posicion = new Punto(0,0);
		this.equipo = equipo;
	}
	
	public void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta {
		if (this.activo == true) {
			if (cantidad <= 0) {
				throw new CantidadDeEnergiaIncorrecta();
			} else {
				if (1 - this.energia >= cantidad) {
					this.energia = this.energia + cantidad;
				} else {
					this.energia = 1;
				}
			}
		}
	}
	
	public void destruir () {
		this.energia = 0;
		this.activo = false;
	}
	
	public void disminuirEnergia (int danio) {
		if(this.energia - danio <= 0) {
			this.destruir();
		} else {
			this.energia = this.energia - danio;
		}
	}
	
	public void mover () {
		if (this.activo == true) {
			if ((this.posicion.getX() >= 99*5)||(this.posicion.getX() <= 2*5)||(this.posicion.getY() >= 99*5)||(this.posicion.getY() <= 2*5)) {
				this.destruir();
			} else {
				this.direccion.trasladar(this, this.tablero);
			}
		}
	}
	
	public void activar(Mision tablero, Punto posicion) {
		this.tablero = tablero;
		this.posicion = posicion;
		this.activo = true;
		this.tablero.ubicarBalaEnPosicion(this, posicion);
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return this.direccion;
	}
	
	public Punto getPosicion() {
		return this.posicion;
	}
	
	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}
	
	public void setExpansible(boolean expansible) {
		this.expansible = expansible;
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
	
	public void setActivo(boolean valor) {
		this.activo = valor;
	}
	
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	public boolean getActivo() {
		return this.activo;
	}

	public int getTamanio() {
		return this.tamanio;
	}

	public int getVelocidad() {
		return this.velocidad;
	}

	public int getEquipo() {
		return this.equipo;
	}

	public boolean getExpansible() {
		return this.expansible;
	}

	public int getDanio() {
		return this.danio;
	}
	
	public Mision getTablero() {
		return this.tablero;
	}
	
	public int getEnergia() {
		return this.energia;
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