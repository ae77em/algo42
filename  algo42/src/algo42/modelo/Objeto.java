package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Objeto implements Movible {

	private Mision tablero;
	private Punto posicion;
	private boolean activo, expansible;
	private Direccion direccion;
	private int velocidad, tamanio, danio, energia, equipo;
	
	public Objeto () {
		this.activo = false;
		this.equipo = 0;
		this.tamanio = 1;
		this.direccion = new Abajo();
		this.velocidad = 1;
		this.posicion = new Punto(0, 0);
		this.expansible = false;
		this.danio = 0;
		this.energia = 2;
	}
	
	public void activar (Mision tablero, Punto posicion) {
		this.activo = true;
		this.tablero = tablero;
		this.posicion = posicion;
	}
	
	public void destruir() {
		this.activo = false;
	}
	
	public void mover() {
		if (this.activo == true) {
			if ((this.posicion.getY()) >= 100) {
				this.destruir();
			} else {
				this.direccion.trasladar(this, tablero);
			}
		}
	}
	
	public abstract void consumirPor (Algo42 algo42);
	
	public void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta {
		if (this.activo == false) {
			if (cantidad <= 0) {
				throw new CantidadDeEnergiaIncorrecta();
			} else {
				if (2 - this.energia >= cantidad) {
					this.energia = this.energia + cantidad;
				} else {
					this.energia = 2;
				}
			}
		}
	}

	public void disminuirEnergia(int cantidad) {
		if(this.energia - danio <= 0) {
			this.destruir();
		} else {
			this.energia = this.energia - danio;
		}
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
	
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public void setActivo(boolean valor) {
		this.activo = valor;
	}
	
	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public Direccion getDireccion() {
		return this.direccion;
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