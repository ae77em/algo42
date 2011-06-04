package algo42.modelo;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Derecha extends Direccion {

	public void disparar(Punto posicion, Mision tablero, Arma arma) {
		Bala bala = arma.getBala();
		Punto posicionBala = new Punto(posicion.getX() + 2, posicion.getY());
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
		
		for (int i = posicion.getX(); i <= posicion.getX() + velocidad; i++) {
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
			posicionNueva = new Punto(posicion.getX() + velocidad, posicion.getY());
			if (posicionNueva.getX() >= 100) {
				posicionNueva = new Punto(99, posicion.getY());
			}
			objetoMovible.setPosicion(posicionNueva);
		}
	}
}