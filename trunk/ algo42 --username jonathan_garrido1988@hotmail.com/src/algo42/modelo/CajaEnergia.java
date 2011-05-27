package algo42.modelo;

public class CajaEnergia extends Objeto {

	private int cantidad;
	
	public CajaEnergia () {
		super();
		this.cantidad = 50;
	}
	
	@Override
	public void consumirPor(Algo42 algo42) {
		if (this.activo == true) {
			algo42.aumentarEnergia(this.cantidad);
			this.destruir();
		}
	}

}
