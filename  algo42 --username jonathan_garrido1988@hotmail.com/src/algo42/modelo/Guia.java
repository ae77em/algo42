package algo42.modelo;

import java.util.ArrayList;
import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Guia extends NaveArmada {
        
	private ArrayList<Nave> navesDeFlota = new ArrayList<Nave>();
	
	public Guia() {
		super(new ZigZag(), 4, 500, 500, 100);
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
        	if ((500 - this.getEnergia()) >= cantidadEnergia) {
                    this.setEnergia(this.getEnergia() + cantidadEnergia);
            } else {
            	this.setEnergia(500);
            }
        }
	}
	
	public void destruir() {
		this.setEnergia(0);
		this.setActivo(false);
		this.getTablero().aumentarPuntaje(this.getPuntaje());
		for (int i = 0; i < (this.navesDeFlota.size()); i++) {
			navesDeFlota.get(i).huir();
		}
	}
	
	public void setNaveDeFlota(Nave nave) {
		this.navesDeFlota.add(nave);
	}
}