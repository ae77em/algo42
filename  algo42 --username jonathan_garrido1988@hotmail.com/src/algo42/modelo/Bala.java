package algo42.modelo;

public abstract class Bala implements Movible {

	@Override
	public Punto getPosicion() {
	
		return null;
	}

	@Override
	public boolean getOcupado() {
	
		return false;
	}

	public void setPosicion(Punto posicion) {
	
		
	}

	@Override
	public void activarMisionEnPosicion(Mision mision, Punto posicion) {
		
		
	}

	@Override
	public int getRadio() {
		
		return 0;
	}

}
