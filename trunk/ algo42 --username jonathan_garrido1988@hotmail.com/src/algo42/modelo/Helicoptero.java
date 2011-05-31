package algo42.modelo;

public class Helicoptero extends Nave {
	
	private int radioDeVuelo;
	private int velocidad;
	
	
	public Helicoptero() {
		radioDeVuelo = 1;
		velocidad = 1;		
	}
		
	Helicoptero(int radioDeVuelo,int velocidad){
		this.radioDeVuelo = radioDeVuelo;
		this.velocidad = velocidad;
	}
	
	activar(){
		// no se que iria aca
	}
	
	getRadioDeVuelo(){
		return radioDeVuelo
	}
	
	setRadioDeVuelo(int otroRadio){
		radioDeVuelo = otroRadio
	}
	
	setVelocidad(int otraVel){
		velocidad = otraVel
	}
	
	getVelocidad(){
		return velocidad
	}
	
}
