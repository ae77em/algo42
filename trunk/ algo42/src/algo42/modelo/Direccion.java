package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Direccion {

	public abstract void disparar(Punto posicion, Mision tablero, Arma arma);

	public abstract void trasladar(Movible objetoMovible, Mision tablero);

	public abstract void persistir(Document doc, Element elemento);
}