package algo42.modelo;

public class Nave implements Movible {
	
	private Punto posicion = new Punto(0,0);
	// inicializo el punto en una posicion invalida

	public void activar(Mision mision) {
		// TODO Auto-generated method stub
		
	}

	public void actuar() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public Punto getPosicion() {
		return posicion;
	}

	@Override
	public boolean getOcupado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPosicion(Punto otraPosicion) {
		posicion = otraPosicion;
	}

	@Override
	public void activarMisionEnPosicion(Mision mision, Punto posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRadio() {
		return radio;
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		// metodo abstracto
		
	}

}
