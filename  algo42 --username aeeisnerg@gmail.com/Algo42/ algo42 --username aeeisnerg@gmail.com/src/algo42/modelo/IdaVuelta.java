package algo42.modelo;

public class IdaVuelta extends EstrategiaArmados {
	
	private int direccion;
	private Punto posicion = new Posicion(0,0);
	
	IdaVuelta(){
		direccion = 1;
	}
	
	/* ¿como hago para saber cuanto tiempo se mueve????
	 **/
	public void mover(){
		if ( this.posicionFueraDelAmbito()){
			this.cambiarDireccion() 
			}
		else 
			{ posicion setY(posicion + direccion) };
	}
		
	private boolean posicionFueraDelAmbito(){
		// aca faltaria ver que no salga fuera del ambito por derecha...
		return ( posicion getX + direccion < 0 );
	}
	
	private void cambiarDireccion(){
		direccion = direccion * -1;
	}

}
