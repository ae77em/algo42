package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Civil extends Nave{
        
	public Civil() {
		this.estrategia = new LineaHorizontal();
		this.velocidad = 1;
		this.energia = 50;
		this.activo = false;
		this.equipo = 2;
		this.expansible = false;
		this.danio = 50;
		this.direccion = null;
		this.tamanio = 1;
		this.posicion = new Punto(0, 0);
		this.puntaje = -300;
	}

	public void activar(Mision tablero, Punto posicion) {	
		this.activo = true;
        this.tablero = tablero;
        this.posicion = posicion;
        this.direccion = new Derecha();
	}

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((50 - this.energia) >= cantidadEnergia) {
                    this.energia = this.energia + cantidadEnergia;
            } else {
            	this.energia = 50;
            }
        }
	}
}
