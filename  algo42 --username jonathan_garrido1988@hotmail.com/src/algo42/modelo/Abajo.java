package algo42.modelo;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Abajo extends Direccion {

	@Override
	public void disparar(Punto posicion, Mision tablero, Arma arma) {
		Bala bala = arma.getBala();
		Punto posicionBala = new Punto(posicion.getX(), posicion.getY() + 2);
		bala.setDireccion(this);
		bala.activar(posicionBala, tablero);
		try {
			tablero.ubicarBalaEnPosicion(bala, posicionBala);
		} catch (CoordenadaFueraDeRangoError e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void trasladar(Movible objetoMovible, Mision tablero) {
		
	}

}
