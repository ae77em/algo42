package algo42.modelo;


public class ContraAliado extends TipoDeChoque {

	public void chocarEntre(Movible autor, Movible victima) {
		if ((autor.getActivo() == true)&&(victima.getActivo() == true)) {
			autor.disminuirEnergia(victima.getDanio());
			victima.disminuirEnergia(autor.getDanio());
		}
	}
}