package algo42.modelo;

public class LineaHorizontal extends Estrategia {
	
	private int sentido;
	
	public LineaHorizontal() {
		this.actualizarDatosDeMovimiento();
	}
	
	public void mover(){
		float movimiento = nave.getPosicion().getY() + nave.getVelocidad()*sentido;
		
		nave.setPosicion(0,movimiento);
		
		this.actualizarDatosDeMovimiento();
	}
	
	private void actualizarDatosDeMovimiento(){
		if ( nave.getPosicion().getY() = 0 ){
			sentido = DERECHA;
		};
		if ( nave.getPosicion().getY() = nave.getLimitesTablero().getY() ){
			sentido = IZQUIERDA;
		}
	}
	

}
