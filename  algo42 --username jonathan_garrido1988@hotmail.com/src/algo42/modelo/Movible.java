package algo42.modelo;

public interface Movible {
	public abstract Punto getPosicion();
	public abstract boolean getOcupado();
	public abstract void setPosicion(Punto posicion);
	public abstract void activarMisionEnPosicion(Mision mision, Punto posicion);
	public abstract int getRadio();
}