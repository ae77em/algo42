package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Izquierda extends Direccion {
	
	public void disparar(Punto posicion, Mision tablero, Arma arma) {
		Bala bala = arma.getBala();
		Punto posicionBala = new Punto(posicion.getX() - 2, posicion.getY());
		bala.setDireccion(this);
		bala.activar(tablero, posicionBala);
		tablero.ubicarBalaEnPosicion(bala, posicionBala);
	}

	public void trasladar(Movible objetoMovible, Mision tablero) {
		Punto posicion = objetoMovible.getPosicion();
		int velocidad = objetoMovible.getVelocidad();
		Movible otroObjeto = null;
		ResolvedorDeChoque resolvedorDeChoque = new ResolvedorDeChoque();
		TipoDeChoque tipoDeChoque;
		Punto posicionNueva;
		
		for (int i = posicion.getX() - velocidad; i <= posicion.getX(); i++) {
			try {
				otroObjeto = tablero.hayAlguien(objetoMovible, new Punto(i, posicion.getY()));
			} catch (CoordenadaFueraDeRangoError e) {
				// Nunca se llega a tirar esta excepcion
			}
			if ((otroObjeto != null)&&(otroObjeto.getActivo() == true)) {
				tipoDeChoque = resolvedorDeChoque.resolver(objetoMovible, otroObjeto);
				tipoDeChoque.chocarEntre(objetoMovible, otroObjeto);
			}
		}
		if (objetoMovible.getActivo() == true) {
			posicionNueva = new Punto(posicion.getX() - velocidad, posicion.getY());
			if (posicionNueva.getX() < 1) {
				posicionNueva = new Punto(1, posicion.getY());
			}
			objetoMovible.setPosicion(posicionNueva);
		}
	}
	
	public void persistir(Document doc, Element elemento) {
		elemento.setTextContent("Izquierda");
	}
}