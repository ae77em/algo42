package algo42.modelo;

public class Helicoptero extends Nave {
	
	public Helicoptero() {
		posicion = new Punto(0,0);
		tamanio = 0.5; //este valor es arbitrario
		velocidad = 0.5; //este valor es arbitrario
		energia = 100; //este valor es arbitrario
		energiaInicial = energia;
		activo = false;
	}
		
		
/*	getRadioDeVuelo(){
		return radioDeVuelo;
	}
	
	setRadioDeVuelo(int otroRadio){
		radioDeVuelo = otroRadio;
	}
	
	setVelocidad(int otraVel){
		velocidad = otraVel;
	}
	
	getVelocidad(){
		return velocidad;
	}*/
	
}
