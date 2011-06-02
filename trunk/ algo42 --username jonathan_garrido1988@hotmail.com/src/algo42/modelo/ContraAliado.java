package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class ContraAliado extends TipoDeChoque {

	public void chocarEntre(Movible autor, Movible victima) {
		if ((autor.getActivo() == true)&&(victima.getActivo() == true)) {
			try {
				autor.disminuirEnergia(victima.getDanio());
				autor.aumentarEnergia(1);
				victima.disminuirEnergia(autor.getDanio());
				victima.aumentarEnergia(1);
			} catch (CantidadDeEnergiaIncorrecta e) {
				e.printStackTrace();
			}
		}
	}
}
