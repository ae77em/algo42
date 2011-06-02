package algo42.modelo;


public class Circulo extends Estrategia {
	
	public void usar(Nave nave, Mision tablero) {
		this.tablero = tablero;
		this.nave = nave;
		Punto posicionDeNave = this.nave.getPosicion();
        
		if ((posicionDeNave.getX() > 21)&&(posicionDeNave.getX() <= 51)&&(posicionDeNave.getY() >= 2)&&(posicionDeNave.getY() < 17)) {
			this.nave.setDireccion(new Izquierda());
			this.mover();
			this.mover();
			this.nave.setDireccion(new Abajo());
			this.mover();
		}
		if ((posicionDeNave.getX() > 6)&&(posicionDeNave.getX() <= 21)&&(posicionDeNave.getY() >= 17)&&(posicionDeNave.getY() < 47)) {
			this.nave.setDireccion(new Izquierda());
			this.mover();
			this.nave.setDireccion(new Abajo());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() >= 6)&&(posicionDeNave.getX() < 21)&&(posicionDeNave.getY() >= 47)&&(posicionDeNave.getY() < 77)) {
			this.nave.setDireccion(new Derecha());
			this.mover();
			this.nave.setDireccion(new Abajo());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() >= 21)&&(posicionDeNave.getX() < 51)&&(posicionDeNave.getY() >= 77)&&(posicionDeNave.getY() < 92)) {
			this.nave.setDireccion(new Derecha());
			this.mover();
			this.mover();
			this.nave.setDireccion(new Abajo());
			this.mover();
		}
		if ((posicionDeNave.getX() >= 51)&&(posicionDeNave.getX() < 81)&&(posicionDeNave.getY() > 77)&&(posicionDeNave.getY() <= 92)) {
			this.nave.setDireccion(new Derecha());
			this.mover();
			this.mover();
			this.nave.setDireccion(new Arriba());
			this.mover();
		}
		if ((posicionDeNave.getX() >= 81)&&(posicionDeNave.getX() < 96)&&(posicionDeNave.getY() > 47)&&(posicionDeNave.getY() <= 81)) {
			this.nave.setDireccion(new Derecha());
			this.mover();
			this.nave.setDireccion(new Arriba());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() > 81)&&(posicionDeNave.getX() <= 96)&&(posicionDeNave.getY() > 17)&&(posicionDeNave.getY() <= 47)) {
			this.nave.setDireccion(new Izquierda());
			this.mover();
			this.nave.setDireccion(new Arriba());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() > 51)&&(posicionDeNave.getX() <= 81)&&(posicionDeNave.getY() > 2)&&(posicionDeNave.getY() <= 17)) {
			this.nave.setDireccion(new Izquierda());
			this.mover();
			this.mover();
			this.nave.setDireccion(new Arriba());
			this.mover();
		}
	}

	public void mover(){
		this.direccion = this.nave.getDireccion();
		this.direccion.trasladar(this.nave, this.tablero);
	}
}
