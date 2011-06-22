package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;
import algo42.titiritero.ObjetoVivo;
import algo42.titiritero.Posicionable;

public interface Movible extends ObjetoVivo, Posicionable {
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
	public abstract void mover();
	public abstract void persistir(Document doc, Element espacioAereo);
}