package algo42.modelo;

public class BalaTorpedoRastreador extends Bala {

	private int contadorDeTiempo;
	
	public BalaTorpedoRastreador(int equipo) {
		super();
		this.velocidad = 5;
		this.danio = 20;
		this.contadorDeTiempo = 0;
	}
	
	public void mover() {
		Punto posicionDelJugador = this.tablero.getPosicionDelJugador();
		if (this.contadorDeTiempo == 20) {
			this.destruir();
		} else {
			this.contadorDeTiempo = this.contadorDeTiempo + 1;
		}
		if (this.activo == true) {
			if (this.posicion.getX() > posicionDelJugador.getX()) {
				this.direccion = new Izquierda();
			}
			if (this.posicion.getX() < posicionDelJugador.getX()) {
				this.direccion = new Derecha();
			}
			if (this.posicion.getY() > posicionDelJugador.getY()) {
				this.direccion = new Arriba();
			}
			if (this.posicion.getY() < posicionDelJugador.getY()) {
				this.direccion = new Abajo();
			}
		}
		this.direccion.trasladar(this, this.tablero);
		if ((this.posicion.getX() > 100)||(this.posicion.getX() < 1)||(this.posicion.getY() > 100)||(this.posicion.getY() < 1)) {
			this.destruir();
		}
	}
}