package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Avioneta extends NaveArmada {
        
	public Avioneta() {
		this.estrategia = new IdaVuelta();
		Laser laser = null;
        try {
        	laser = new Laser(-1, 2);
        } catch (CantidadDeBalasIncorrecta e) {
        	e.printStackTrace();
        }
        this.armas.add(laser);
        this.velocidad = 3;
        this.energia = 150;
        this.activo = false;
        this.equipo = 2;
        this.expansible = false;
        this.danio = 150;
        this.direccion = null;
        this.tamanio = 1;
        this.posicion = new Punto(0, 0);
        this.puntaje = 20;
	}
	
	public void activar(Mision tablero, Punto posicion) {
		this.activo = true;
        this.tablero = tablero;
        this.posicion = posicion;
    	this.direccion = new Abajo();
    }

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((150 - this.energia) >= cantidadEnergia) {
                    this.energia = this.energia + cantidadEnergia;
            } else {
            	this.energia = 150;
            }
        }
	}
}
