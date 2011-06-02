package algo42.modelo;

import java.util.Iterator;

public class IdaVuelta extends EstrategiaArmados {
        
	private boolean vueltaYaHecha = false;
	
	public void usar(Nave nave, Mision tablero) {
		this.tablero = tablero;
		this.nave = nave;
		Punto posicionDeNave = this.nave.getPosicion();
		
		if ((posicionDeNave.getY() == 2)&&(this.vueltaYaHecha == true)) {
			this.nave.huir();
		}
		if (posicionDeNave.getY() == 99) {
			this.girar();
			this.mover();
		} else {
			this.mover();
			this.disparar();
		}
	}

	public void mover() {
		this.direccion = this.nave.getDireccion();
		this.direccion.trasladar(this.nave, this.tablero);
	}

	public void disparar() {
		Iterator<Arma> iterador = ((NaveArmada) this.nave).getArmas().iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.nave.getPosicion(), this.tablero, this.direccion);
    	} 
	}
	
	public void girar() {
		this.nave.setDireccion(new Arriba());
		this.vueltaYaHecha = true;
	}
}
