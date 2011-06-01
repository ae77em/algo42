package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Bala extends Objeto {

	protected Mision tablero;
	protected int velocidad;
	protected int danio;
	protected Punto posicion;
	protected boolean activo;
	protected Direccion direccion;
	protected int equipo;
	protected int energia;
	protected int tamanio;
	private boolean expansible;
	
	public Bala () {
		this.energia = 1;
		this.tamanio = 0;
		this.activo = false;
		this.expansible = false;
		this.posicion = new Punto(0,0);
	}
	
	public void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta {
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
		if (this.activo = true) {
			if ((this.posicion.getX() > 100)||(this.posicion.getX() < 1)||(this.posicion.getY() > 100)||(this.posicion.getY() < 1)) {
				this.activo = false;
			} else {
				this.direccion.trasladar(this, this.tablero);
			}
		}
	}
	
	@Override
	public void activar(Mision tablero, Punto posicion) {
		this.tablero = tablero;
		this.posicion = posicion;
		this.activo = true;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public boolean getActivo() {
		return this.activo;
	}

	@Override
	public Punto getPosicion() {
		return this.posicion;
	}

	@Override
	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}

	@Override
	public int getTamanio() {
		return this.tamanio;
	}

	@Override
	public int getVelocidad() {
		return this.velocidad;
	}

	@Override
	public int getEquipo() {
		return this.equipo;
	}

	@Override
	public boolean getExpansible() {
		return this.expansible;
	}

	@Override
	public int getDanio() {
		return this.danio;
	}
	

	@Override
	public void consumirPor(Algo42 algo42) {
		algo42.disminuirEnergia(this.danio);
		this.destruir();
	}
}
