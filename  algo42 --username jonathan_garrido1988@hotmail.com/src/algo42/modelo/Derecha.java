package algo42.modelo;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Derecha extends Direccion {

	@Override
	public void disparar(Punto posicion, Mision tablero, Arma arma) {
		Bala bala = arma.getBala();
		Punto posicionBala = new Punto(posicion.getX() + 2, posicion.getY());
		bala.setDireccion(this);
		bala.activar(tablero, posicionBala);
		tablero.ubicarBalaEnPosicion(bala, posicionBala);
		
	}

	@Override
	public void trasladar(Movible objetoMovible, Mision tablero) {
		Punto posicion = objetoMovible.getPosicion();
		int velocidad = objetoMovible.getVelocidad();
		int tamanio = objetoMovible.getTamanio();
		Movible otroObjeto = null;
		ResolvedorDeChoque resolvedorDeChoque = new ResolvedorDeChoque();
		TipoDeChoque tipoDeChoque;
		Punto posicionNueva;
		
		for (int i = posicion.getX() + tamanio + 1; i <= posicion.getX() + tamanio + velocidad; i++) {
			for (int j = posicion.getY() - tamanio; j <= posicion.getY() + tamanio; j++) {
				try {
					otroObjeto = tablero.hayAlguien(new Punto(i, j));
				} catch (CoordenadaFueraDeRangoError e) {
					e.printStackTrace();
				}
				if ((otroObjeto != null)&&(otroObjeto.getActivo() == true)) {
					tipoDeChoque = resolvedorDeChoque.resolver(objetoMovible, otroObjeto);
					tipoDeChoque.chocarEntre(objetoMovible, otroObjeto);
				}
			}
		}
		if (objetoMovible.getActivo() == true) {
			posicionNueva = new Punto(posicion.getX() + velocidad, posicion.getY());
			if (posicionNueva.getX() > 100) {
				posicionNueva = new Punto(100, posicion.getY());
			}
			objetoMovible.setPosicion(posicionNueva);
		}
	}
	
}
