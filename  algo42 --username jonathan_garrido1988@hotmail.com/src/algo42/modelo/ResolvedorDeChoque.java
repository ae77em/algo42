package algo42.modelo;

public class ResolvedorDeChoque {

	public TipoDeChoque resolver(Movible autor, Movible victima) {
		int equipoAutor = autor.getEquipo();
		int equipoVictima = victima.getEquipo();
		if (equipoAutor == 0) {
			if (equipoVictima == 1) {
				if (victima.getExpansible() == true) {
					return new ContraConsumible();
				} else {
					return new ContraAliado();
				}
			} else {
				return new ContraAliado();
			}
		} else {
			if (equipoVictima == 0) {
				if (autor.getExpansible() == true) {
					return new ContraConsumible();
				} else {
					return new ContraAliado();
				}
			} else {
				if (equipoVictima == equipoAutor) {
					return new ContraAliado();
				} else {
					return new ContraEnemigo();
				}
			}
		}
	}
}
