package algo42.modelo;

public class LineaHorizontal extends Estrategia {
        
	public void usar(Nave nave, Mision tablero) {
		this.tablero = tablero;
		this.nave = nave;
		Punto posicionDeNave = this.nave.getPosicion();
		
		if (posicionDeNave.getY() == 99) {
			this.nave.huir();
		} else {
			this.mover();
		}
	}

	public void mover() {
		this.direccion = this.nave.getDireccion();
		this.direccion.trasladar(this.nave, this.tablero);
	}
}
