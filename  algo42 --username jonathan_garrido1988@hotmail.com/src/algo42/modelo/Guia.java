package algo42.modelo;

import java.util.ArrayList;
import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Guia extends NaveArmada {
        
	private ArrayList<Nave> navesDeFlota;
	
	public Guia() {
		this.estrategia = new ZigZag();
		Laser laser = null;
		Cohete cohete = null;
		TorpedoRastreador torpedoRastreador = null;
        try {
        	laser = new Laser(-1, 2);
        	cohete = new Cohete(-1, 2);
        	torpedoRastreador = new TorpedoRastreador(-1, 2);
        } catch (CantidadDeBalasIncorrecta e) {
        	e.printStackTrace();
        }
        this.armas.add(laser);
        this.armas.add(cohete);
        this.armas.add(torpedoRastreador);
        this.velocidad = 4;
        this.energia = 500;
        this.activo = false;
        this.equipo = 2;
        this.expansible = false;
        this.danio = 500;
        this.direccion = null;
        this.tamanio = 1;
        this.posicion = new Punto(0, 0);
        this.puntaje = 100;
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
        	if ((500 - this.energia) >= cantidadEnergia) {
                    this.energia = this.energia + cantidadEnergia;
            } else {
            	this.energia = 500;
            }
        }
	}
	
	public void destruir() {
		this.energia = 0;
		this.activo = false;
		this.tablero.aumentarPuntaje(this.puntaje);
		for (int i = 0; i < (this.navesDeFlota.size()); i++) {
			navesDeFlota.get(i).huir();
		}
	}
	
	public void setNaveDeFlota(Nave nave) {
		this.navesDeFlota.add(nave);
	}
}