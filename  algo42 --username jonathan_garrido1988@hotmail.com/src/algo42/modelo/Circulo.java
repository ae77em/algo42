import java.lang.Math.*;
package algo42.modelo;

public class Circulo extends Estrategia {
	
	private float angulo;
	private float radio;
	
	Circulo(float unRadio){
		angulo = PI;
		radio = unRadio;
	}
	
	mover(){
		angulo = angulo + (nave.getVelocidad() / radio);
		
		float movimiento_x = centro.getX() + (radio * cos(angulo));
		float movimiento_y = centro.getY() + (radio * sin(angulo));
				
		posicion.setX( movimiento_x );
		posicion.setY( movimiento_y );
	}

	
}
