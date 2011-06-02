package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Objeto implements Movible {

	private Mision tablero;
	private Punto posicion;
	protected boolean activo;
	private int equipo;
	private Direccion direccion;
	private int velocidad;
	private int tamanio;
	private boolean expansible;
	private int danio;
	private int energia;
	
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
		this.tablero.ubicarObjetoEnPosicion(this, posicion);
	}
	
	public void destruir() {
		this.activo = false;
	}
	
	public void mover() {
		if (this.activo = true) {
			if (this.posicion.getY() >= 100) {
				this.destruir();
			} else {
				this.direccion.trasladar(this, tablero);
			}
		}
	}
	
	public abstract void consumirPor (Algo42 algo42);
	
	public void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta {
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

}