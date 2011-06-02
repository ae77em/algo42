package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class Laser extends Arma {
	
	public Laser(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaLaser balaLaser = new BalaLaser(this.equipo);
		return balaLaser;
	}
}
