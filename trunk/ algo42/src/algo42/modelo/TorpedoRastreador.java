package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoRastreador extends Arma {

	public TorpedoRastreador(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaLaser balaLaser = new BalaLaser(this.getEquipo());
		return balaLaser;
	}
}