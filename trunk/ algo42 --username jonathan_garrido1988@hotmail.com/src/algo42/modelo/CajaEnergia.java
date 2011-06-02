package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class CajaEnergia extends Objeto {

	private int cantidad;
	
	public CajaEnergia() {
		super();
		this.cantidad = 50;
	}
	
	public void consumirPor(Algo42 algo42) {
		if (this.activo == true) {
			try {
				algo42.aumentarEnergia(this.cantidad);
			} catch (CantidadDeEnergiaIncorrecta e) {
				e.printStackTrace();
			}
			this.destruir();
		}
	}
}
