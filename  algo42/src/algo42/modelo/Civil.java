package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Civil extends Nave {
        
	public Civil() {
		super(new LineaHorizontal(), 1, 50, 50, -300);
	}

	public void activar(Mision tablero, Punto posicion) {	
		this.setActivo(true);
        this.setTablero(tablero);
        this.setPosicion(posicion);
        this.setDireccion(new Derecha());
	}

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0) {
    		throw new CantidadDeEnergiaIncorrecta();
        } else {
        	if ((50 - this.getEnergia()) >= cantidadEnergia) {
                    this.setEnergia(this.getEnergia() + cantidadEnergia);
            } else {
            	this.setEnergia(50);
            }
        }
	}
}