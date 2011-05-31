import java.lang.Math.*;
package algo42.modelo;

public class ZigZag extends EstrategiaArmados {
	
	private int indicadorDeSentido;
	private int periodoDeVueloHaciaUnLado;
	private Punto posicion;
	private Nave nave;
	private Mision mision;
	
	private void actualizarDatosDeMovimiento(){
		//actualizo la cantidad de movimientos restantes
		//hacia el lado que se esta moviendo
		periodoDeVueloHaciaUnLado = periodoDeVueloHaciaUnLado - 1;
		
		//actualizo el director de dicho movimiento
		if (periodoDeVueloHaciaUnLado = 0){
			indicadorDeSentido = indicadorDeSentido * -1;
			//el valor del perriodoDeVueloHaciaUnLado es arbitrario...
			periodoDeVueloHaciaUnLado = 3;
		}
	}
	
	public void mover(){
		float longitudDeMovimiento = 1/sqrt(2);
		
		float movimiento_x = posicion.getX() + longitudDeMovimiento;
		float movimiento_y = posicion.getY() + longitudDeMovimiento * indicadorDeSentido;
		
		posicion.setX( movimiento_x );
		posicion.setY( movimiento_y );
		
		this.actualizarDatosDeMovimiento();
	}
		
}
