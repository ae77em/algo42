package algo42.modelo;

public class ContraConsumible extends TipoDeChoque {

	@Override
	public void chocarEntre(Movible autor, Movible victima) {
		if ((autor.getActivo() == true)&&(victima.getActivo() == true)) {
			if (autor.getExpansible() == true) {
				((Objeto) victima).consumirPor((Algo42) autor);
			} else {
				((Objeto) autor).consumirPor((Algo42) victima);
			}
		} 
	}

}
