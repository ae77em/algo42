package algo42.modelo;

import java.util.Calendar;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoAdaptable extends Arma {

	private Arma armaQueUsa;
	private int tiempoDesdeEleccionDeArma;
	
	public TorpedoAdaptable(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
		int numeroAlAzar = (int) (Math.random()*100+1);
		if ((numeroAlAzar >= 0)&&(numeroAlAzar < 50)) { 			// 50% de probabilidad de usar laser
			this.armaQueUsa = new Laser(-1,2);
		} else {
			if ((numeroAlAzar >= 50)&&(numeroAlAzar < 70)) {		// 20% de probabilidad de usar cohete
				this.armaQueUsa = new Cohete(-1,2);
			} else {
				if ((numeroAlAzar >= 70)&&(numeroAlAzar < 90)) {	// 20% de probabilidad de usar torpedos simples
					this.armaQueUsa = new TorpedoSimple(-1,2);
				} else {
					this.armaQueUsa = new TorpedoRastreador(-1,2);	// 10% de probabilidad de usar torpedos rastreadores
				}
			}
		}
		Calendar calendario = Calendar.getInstance();
		this.tiempoDesdeEleccionDeArma = calendario.get(Calendar.SECOND);
	}

	public void cambiarArmaQueUsa(NaveArmada nave) {
		nave.removerTodasLasArmas();
		try {
			nave.agregarArma(new TorpedoAdaptable(-1, 2));
		} catch (CantidadDeBalasIncorrecta e) {
			// Nunca se llega a tirar esta excepcion
		}
	}
	
	public Bala getBala() {
		Calendar calendario = Calendar.getInstance();
		int tiempoActual = calendario.get(Calendar.SECOND);
		if (tiempoActual - this.tiempoDesdeEleccionDeArma >= 10) { // Cada 10 segundos cambia el rama con que dispara
			this.cambiarArmaQueUsa(this.getNave());
		}
		return this.armaQueUsa.getBala();
	}

}
