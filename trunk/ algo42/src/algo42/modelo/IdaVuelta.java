package algo42.modelo;

import java.util.Iterator;

public class IdaVuelta extends EstrategiaArmados {
        
	private boolean vueltaYaHecha = false;
	
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);
		Punto posicionDeNave = this.getNave().getPosicion();
		
		if ((posicionDeNave.getY() == 2)&&(this.vueltaYaHecha == true)) {
			this.getNave().huir();
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
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}

	public void disparar() {
		Iterator<Arma> iterador = ((NaveArmada) this.getNave()).getArmas().iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.getNave().getPosicion(), this.getTablero(), this.getDireccion());
    	} 
	}
	
	public void girar() {
		this.getNave().setDireccion(new Arriba());
		this.vueltaYaHecha = true;
	}
}