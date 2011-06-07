package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoSimple extends Arma {

	public TorpedoSimple(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaLaser balaLaser = new BalaLaser(this.getEquipo());
		return balaLaser;
	}
}