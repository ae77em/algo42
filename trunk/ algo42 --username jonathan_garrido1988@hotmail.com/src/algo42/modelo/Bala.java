package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class Bala implements Movible {

	protected Mision tablero;
	protected int velocidad;
	protected int danio;
	protected Punto posicion;
	protected boolean activo;
	protected Direccion direccion;
	protected int equipo;
	protected int energia;
	protected int tamanio;
	
	public Bala () {
		this.energia = 1;
		this.tamanio = 0;
		this.activo = false;
		this.posicion = new Punto(0,0);
	}
	
	public void aumentarEnergia (int cantidad) throws CantidadDeEnergiaIncorrecta {
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
	
	public void activar (Punto posicionBala, Mision tablero) {
		this.tablero = tablero;
		this.posicion = posicionBala;
		this.activo = true;
	}
	
	
	@Override
	public Punto getPosicion() {
		return null;
	}

	@Override
	public boolean getOcupado() {
		return false;
	}
	
	@Override
	public void setPosicion(Punto posicion) {
	
	}

	@Override
	public void activarMisionEnPosicion(Mision mision, Punto posicion) {
		
	}

	@Override
	public int getRadio() {
		return 0;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

}
