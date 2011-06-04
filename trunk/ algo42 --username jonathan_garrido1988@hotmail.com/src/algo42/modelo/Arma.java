package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public abstract class Arma {
	
	private NaveArmada nave;
	private int cantidadDeBalas;
	private int equipo;
	
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
			direccion.disparar(posicion, tablero, this);
		}
		if (this.cantidadDeBalas == -1) {
			direccion.disparar(posicion, tablero, this);
		}
	}

	public abstract Bala getBala();
	
	public int getEquipo() {
		return this.equipo;
	}
	
	public void setNave(NaveArmada nave) {
		this.nave = nave;
	}
	
	public NaveArmada getNave() {
		return this.nave;
	}
}