package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Caza extends NaveArmada {
        
	public Caza() {

		super(new IdaVuelta(), 2, 250, 250, 30);
		int numeroAlAzar = (int) (Math.random()*2+1);
		try {
			if (numeroAlAzar == 1) {
				super.agregarArma(new TorpedoSimple(-1, 2));
			} else {
				Arma arma = new TorpedoAdaptable(-1, 2);
				super.agregarArma(arma);
				arma.setNave(this);
			}
		} catch (CantidadDeBalasIncorrecta e) {
			// Nunca se llega a tirar esta excepcion
		}
	}
	
	public void activar(Mision tablero, Punto posicion) {
		this.setActivo(true);
        this.setTablero(tablero);
        this.setPosicion(posicion);
        this.setDireccion(new Abajo());
	}
	
	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((250 - this.getEnergia()) >= cantidadEnergia) {
                    this.setEnergia(this.getEnergia() + cantidadEnergia);
            } else {
            	this.setEnergia(250);
            }
        }
	}
	
	public void destruir() {
		this.setEnergia(0);
		this.setActivo(false);
		this.getTablero().aumentarPuntaje(this.getPuntaje());
		this.getTablero().ubicarObjetoEnPosicion(new CajaEnergia(), this.getPosicion());
	}
}