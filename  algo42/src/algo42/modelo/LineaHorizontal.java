package algo42.modelo;

public class LineaHorizontal extends Estrategia {
        
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);
		Punto posicionDeNave = this.getNave().getPosicion();
		
		if (posicionDeNave.getY() == 99) {
			this.getNave().huir();
		} else {
			this.mover();
		}
	}

	public void mover() {
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}
}