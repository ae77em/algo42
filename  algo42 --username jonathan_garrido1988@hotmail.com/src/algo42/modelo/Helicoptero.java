package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Helicoptero extends Nave {
        
	public Helicoptero() {
		this.estrategia = new Circulo();
		this.velocidad = 1;
		this.energia = 50;
		this.activo = false;
		this.equipo = 2;
		this.expansible = false;
		this.danio = 50;
		this.direccion = null;
		this.tamanio = 1;
		this.posicion = new Punto(0, 0);
		this.puntaje = -200;
	}

	public void activar(Mision tablero, Punto posicion) {
		this.activo = true;
        this.tablero = tablero;
        this.posicion = posicion;
	}

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((100 - this.energia) >= cantidadEnergia) {
                    this.energia = this.energia + cantidadEnergia;
            } else {
            	this.energia = 100;
            }
        }
	}   
}