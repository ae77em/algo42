package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public interface Movible {
	public abstract void mover();
	public abstract void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta;
	public abstract void disminuirEnergia(int cantidad);
	public abstract Punto getPosicion();
	public abstract int getVelocidad();
	public abstract int getTamanio();
	public abstract boolean getActivo();
	public abstract int getEquipo();
	public abstract boolean getExpansible();
	public abstract int getDanio();
	public abstract void setPosicion(Punto posicion);
	public abstract void activar(Mision tablero, Punto posicion);
}