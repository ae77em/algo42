package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public abstract class Arma {
	
	private int cantidadDeBalas;
	protected int equipo;
	
	public Arma (int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		this.cargar(cantidadDeBalas);
		this.equipo = equipo;
	}
	
	public void cargar (int cantidadDeBalas) throws CantidadDeBalasIncorrecta {
		if(cantidadDeBalas <= -2) {
			throw new CantidadDeBalasIncorrecta();
		} else {
			this.cantidadDeBalas = cantidadDeBalas;
		}
	}
	
	public void disparar(Punto posicion, Mision tablero, Direccion direccion) {
		if (this.cantidadDeBalas >= 1) {
			this.cantidadDeBalas = this.cantidadDeBalas - 1;
		}
		direccion.disparar(posicion, tablero, this);
	}

	public abstract Bala getBala();
}
