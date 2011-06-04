package algo42.modelo;

import java.util.ArrayList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class CajaArmas extends Objeto {

	private ArrayList<Arma> armas;
	
	public CajaArmas() {
		super();
		Cohete cohete = null;
		TorpedoRastreador torpedoRastreador = null;
		
		try {
			cohete = new Cohete(30, 2);
			torpedoRastreador = new TorpedoRastreador(20, 2);		
		} catch (CantidadDeBalasIncorrecta e) {
			e.printStackTrace();
		}
		this.armas.add(cohete);
		this.armas.add(torpedoRastreador);
	}
	
	public void consumirPor(Algo42 algo42) {
		if (this.getActivo() == true) {
			for (int i = 1; i <= armas.size(); i++) {
				algo42.cargar(armas.remove(i));
			}
			this.destruir();
		}
	}
}