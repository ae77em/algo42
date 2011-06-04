package algo42.modelo;

import java.util.Iterator;

public class ZigZag extends EstrategiaArmados {
        
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);	
		this.mover();
		this.disparar();
	}

	public void mover() {
		Punto posicionDeNave = this.getNave().getPosicion();
		int numeroAlAzar = 0;
		if ((posicionDeNave.getY() != 99 )&&(posicionDeNave.getY() != 2)&&(posicionDeNave.getX() != 2)&&(posicionDeNave.getX() != 99)) {
			numeroAlAzar = (int) (Math.random()*4+1);
		}
		if ((posicionDeNave.getY() == 99)||(numeroAlAzar == 1)) {
			this.getNave().setDireccion(new Arriba());
		}
		if ((posicionDeNave.getX() == 99)||(numeroAlAzar == 2)) {
			this.getNave().setDireccion(new Izquierda());
		}
		if ((posicionDeNave.getY() == 2)||(numeroAlAzar == 3)) {
			this.getNave().setDireccion(new Abajo());
		}
		if ((posicionDeNave.getX() == 2)||(numeroAlAzar == 4)) {
			this.getNave().setDireccion(new Derecha());
		}
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}

	public void disparar() {
		Iterator<Arma> iterador = ((NaveArmada) this.getNave()).getArmas().iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.getNave().getPosicion(), this.getTablero(), this.getDireccion());
    	} 
	}          
}