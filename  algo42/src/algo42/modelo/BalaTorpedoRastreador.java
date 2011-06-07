package algo42.modelo;

public class BalaTorpedoRastreador extends Bala {

	private int contadorDeTiempo;
	
	public BalaTorpedoRastreador(int unEquipo) {
		super(unEquipo, 5, 20);
		this.contadorDeTiempo = 0;
	}
	
	public void mover() {
		Punto posicionDelJugador = this.getTablero().getPosicionDelJugador();
		if (this.contadorDeTiempo == 20) {
			this.destruir();
		} else {
			this.contadorDeTiempo = this.contadorDeTiempo + 1;
		}
		if (this.getActivo() == true) {
			if (this.getPosicion().getX() > posicionDelJugador.getX()) {
				this.setDireccion(new Izquierda());
			}
			if (this.getPosicion().getX() < posicionDelJugador.getX()) {
				this.setDireccion(new Derecha());
			}
			if (this.getPosicion().getY() > posicionDelJugador.getY()) {
				this.setDireccion(new Arriba());
			}
			if (this.getPosicion().getY() < posicionDelJugador.getY()) {
				this.setDireccion(new Abajo());
			}
		}
		this.getDireccion().trasladar(this, this.getTablero());
		if ((this.getPosicion().getX() > 100)||(this.getPosicion().getX() < 1)||(this.getPosicion().getY() > 100)||(this.getPosicion().getY() < 1)) {
			this.destruir();
		}
	}
}