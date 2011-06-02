package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Caza extends NaveArmada {
        
	public Caza() {
		this.estrategia = new IdaVuelta();
		TorpedoSimple torpedoSimple = null;
        try {
        	torpedoSimple = new TorpedoSimple(-1, 2);
        } catch (CantidadDeBalasIncorrecta e) {
        	e.printStackTrace();
        }
        this.armas.add(torpedoSimple);
        this.velocidad = 2;
        this.energia = 250;
        this.activo = false;
        this.equipo = 2;
        this.expansible = false;
        this.danio = 250;
        this.direccion = null;
        this.tamanio = 1;
        this.posicion = new Punto(0, 0);
        this.puntaje = 30;
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
        	if ((250 - this.energia) >= cantidadEnergia) {
                    this.energia = this.energia + cantidadEnergia;
            } else {
            	this.energia = 250;
            }
        }
	}
	
	public void destruir() {
		this.energia = 0;
		this.activo = false;
		this.tablero.aumentarPuntaje(this.puntaje);
		this.tablero.ubicarObjetoEnPosicion(new CajaEnergia(), this.posicion);
	}
}
