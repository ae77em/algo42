package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Explorador extends Nave {
        
	public Explorador() {
		this.estrategia = new Circulo();
		this.velocidad = 3;
		this.energia = 100;
		this.activo = false;
		this.equipo = 2;
		this.expansible = false;
		this.danio = 100;
		this.direccion = null;
		this.tamanio = 1;
		this.posicion = new Punto(0, 0);
		this.puntaje = 50;
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
