package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Arriba extends Direccion {
	
	public void disparar(Punto posicion, Mision tablero, Arma arma) {
		Bala bala = arma.getBala();
		Punto posicionBala = new Punto(posicion.getX(), posicion.getY() - 2);
		bala.setDireccion(this);
		bala.activar(tablero, posicionBala);
	}

	public void trasladar(Movible objetoMovible, Mision tablero) {
		Punto posicion = objetoMovible.getPosicion();
		int velocidad = objetoMovible.getVelocidad();
		Movible otroObjeto = null;
		ResolvedorDeChoque resolvedorDeChoque = new ResolvedorDeChoque();
		TipoDeChoque tipoDeChoque;
		Punto posicionNueva;
		
		for (int i = posicion.getY() - velocidad; i <= posicion.getY(); i++) {
			try {
				otroObjeto = tablero.hayAlguien(objetoMovible, new Punto(posicion.getX(), i));
			} catch (CoordenadaFueraDeRangoError e) {
				// Nunca se llega a tirar esta excepcion
			}
			if ((otroObjeto != null)&&(otroObjeto.getActivo() == true)) {
				tipoDeChoque = resolvedorDeChoque.resolver(objetoMovible, otroObjeto);
				tipoDeChoque.chocarEntre(objetoMovible, otroObjeto);
			}
		}
		if (objetoMovible.getActivo() == true) {
			posicionNueva = new Punto(posicion.getX(), posicion.getY() - velocidad);
			if (posicionNueva.getY() <= (objetoMovible.getTamanio())) {
				posicionNueva = new Punto(posicion.getX(), objetoMovible.getTamanio());
			}
			objetoMovible.setPosicion(posicionNueva);
		}
	}
	
	public void persistir(Document doc, Element elemento) {
		elemento.setTextContent("Arriba");
	}
}