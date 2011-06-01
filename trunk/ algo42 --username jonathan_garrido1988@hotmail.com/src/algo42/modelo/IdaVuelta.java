package algo42.modelo;

public class IdaVuelta extends EstrategiaArmados {
	
	private int direccion;
	private Punto posicion;
	
	IdaVuelta(){
		direccion = 1;
	    posicion = new Punto(0,0);
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
		// aca faltaria ver que no salga fuera del ambito por abajo...
		return ( posicion.getX() + direccion = 0 );
	}
	
	private void cambiarDireccion(){
		direccion = direccion * -1;
	}
	
	

}
