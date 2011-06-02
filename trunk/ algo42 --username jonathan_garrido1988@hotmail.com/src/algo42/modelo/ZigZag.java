package algo42.modelo;

import java.util.Iterator;

public class ZigZag extends EstrategiaArmados {
        
	public void usar(Nave nave, Mision tablero) {
		this.tablero = tablero;
		this.nave = nave;	
		
		this.mover();
		this.disparar();
	}

	public void mover() {
		Punto posicionDeNave = this.nave.getPosicion();
		int numeroAlAzar = 0;
		if ((posicionDeNave.getY() != 99 )&&(posicionDeNave.getY() != 2)&&(posicionDeNave.getX() != 2)&&(posicionDeNave.getX() != 99)) {
			numeroAlAzar = (int) (Math.random()*4+1);
		}
		if ((posicionDeNave.getY() == 99)||(numeroAlAzar == 1)) {
			this.nave.setDireccion(new Arriba());
		}
		if ((posicionDeNave.getX() == 99)||(numeroAlAzar == 2)) {
			this.nave.setDireccion(new Izquierda());
		}
		if ((posicionDeNave.getY() == 2)||(numeroAlAzar == 3)) {
			this.nave.setDireccion(new Abajo());
		}
		if ((posicionDeNave.getX() == 2)||(numeroAlAzar == 4)) {
			this.nave.setDireccion(new Derecha());
		}
		this.direccion = this.nave.getDireccion();
		this.direccion.trasladar(this.nave, this.tablero);
	}

	public void disparar() {
		Iterator<Arma> iterador = ((NaveArmada) this.nave).getArmas().iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.nave.getPosicion(), this.tablero, this.direccion);
    	} 
	}          
}