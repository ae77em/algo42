package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Explorador extends Nave {
        
	public Explorador() {
		super(new Circulo(), 3, 100, 100, 50);
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
        	if ((100 - this.getEnergia()) >= cantidadEnergia) {
                    this.setEnergia(this.getEnergia() + cantidadEnergia);
            } else {
            	this.setEnergia(100);
            }
        }
	}    
}