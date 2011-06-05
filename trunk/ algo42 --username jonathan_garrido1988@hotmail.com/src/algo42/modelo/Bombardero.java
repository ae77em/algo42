package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Bombardero extends NaveArmada {
	
	public Bombardero() {

		super(new ZigZag(), 3, 150, 150, 20);
		try {
			super.agregarArma(new Laser(-1, 2));
			super.agregarArma(new Cohete(-1, 2)); 
			super.agregarArma(new TorpedoRastreador(-1, 2)); 
		} catch (CantidadDeBalasIncorrecta e) {
			// Nunca se llega a tirar esta excepcion
		} 
	}
	
	public void activar(Mision tablero, Punto posicion) {
		this.setActivo(true);
        this.setTablero(tablero);
        this.setPosicion(posicion);
	}

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((150 - this.getEnergia()) >= cantidadEnergia) {
                    this.setEnergia(this.getEnergia() + cantidadEnergia);
            } else {
            	this.setEnergia(150);
            }
        }
	}
	
	public void destruir() {
		this.setEnergia(0);
		this.setActivo(false);
		this.getTablero().aumentarPuntaje(this.getPuntaje());
		this.getTablero().ubicarObjetoEnPosicion(new CajaArmas(), this.getPosicion());
	}
}