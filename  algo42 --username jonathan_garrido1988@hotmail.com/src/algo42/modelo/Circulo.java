package algo42.modelo;

public class Circulo extends Estrategia {
	
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);
		Punto posicionDeNave = this.getNave().getPosicion();
        
		if ((posicionDeNave.getX() > 21)&&(posicionDeNave.getX() <= 51)&&(posicionDeNave.getY() >= 2)&&(posicionDeNave.getY() < 17)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
		}
		if ((posicionDeNave.getX() > 6)&&(posicionDeNave.getX() <= 21)&&(posicionDeNave.getY() >= 17)&&(posicionDeNave.getY() < 47)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() >= 6)&&(posicionDeNave.getX() < 21)&&(posicionDeNave.getY() >= 47)&&(posicionDeNave.getY() < 77)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() >= 21)&&(posicionDeNave.getX() < 51)&&(posicionDeNave.getY() >= 77)&&(posicionDeNave.getY() < 92)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
		}
		if ((posicionDeNave.getX() >= 51)&&(posicionDeNave.getX() < 81)&&(posicionDeNave.getY() > 77)&&(posicionDeNave.getY() <= 92)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
		}
		if ((posicionDeNave.getX() >= 81)&&(posicionDeNave.getX() < 96)&&(posicionDeNave.getY() > 47)&&(posicionDeNave.getY() <= 81)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() > 81)&&(posicionDeNave.getX() <= 96)&&(posicionDeNave.getY() > 17)&&(posicionDeNave.getY() <= 47)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() > 51)&&(posicionDeNave.getX() <= 81)&&(posicionDeNave.getY() > 2)&&(posicionDeNave.getY() <= 17)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
		}
	}

	public void mover() {
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}
}