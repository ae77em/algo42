package algo42.modelo;

import java.util.ArrayList;

public abstract class NaveArmada extends Nave {

	private ArrayList<Arma> armas;
	
	public NaveArmada(Estrategia estrategia, int velocidad, int energia, int danio, int puntaje) {
		super(estrategia, velocidad, energia, danio, puntaje);
	}
	
	public ArrayList<Arma> getArmas() {
		return this.armas;
	}

	public void agregarArma(Arma arma) {
		this.armas.add(arma);
	}

	public void removerTodasLasArmas() {
		this.armas.clear();
	}	
}