package algo42.modelo;

import java.util.ArrayList;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public abstract class NaveArmada extends Nave {

	protected ArrayList<Arma> armas;

	public abstract void aumentarEnergia(int cantidad) throws CantidadDeEnergiaIncorrecta;
	
	public ArrayList<Arma> getArma() {
		return this.armas;
	}
	
	public ArrayList<Arma> getArmas() {
		return this.armas;
	}
	
}
