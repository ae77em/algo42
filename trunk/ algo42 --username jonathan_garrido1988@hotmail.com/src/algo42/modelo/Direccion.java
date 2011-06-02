package algo42.modelo;

public abstract class Direccion {

	public abstract void disparar(Punto posicion, Mision tablero, Arma arma);

	public abstract void trasladar(Movible objetoMovible, Mision tablero);
}
