package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class Cohete extends Arma {

	public Cohete(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaCohete balaCohete = new BalaCohete(this.getEquipo());
		return balaCohete;
	}
}