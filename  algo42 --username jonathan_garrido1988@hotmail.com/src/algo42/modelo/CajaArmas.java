package algo42.modelo;

import java.util.ArrayList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class CajaArmas extends Objeto {

	private ArrayList<Arma> armas;
	
	public CajaArmas () {
		super();
		this.armas = new ArrayList<Arma>();
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
	
	@Override
	public void consumirPor(Algo42 algo42) {
		if (this.activo == true) {
			for (int i = 0; i <= armas.size(); i++) {
				algo42.cargar(armas.remove(0));
			}
			this.destruir();
		}
	}

}
