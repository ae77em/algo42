package algo42.modelo;

public class ContraEnemigo extends TipoDeChoque {

	public void chocarEntre(Movible autor, Movible victima) {
		if ((autor.getActivo() == true)&&(victima.getActivo() == true)) {
			victima.disminuirEnergia(autor.getDanio());
			autor.disminuirEnergia(victima.getDanio());
		}
	}
}
