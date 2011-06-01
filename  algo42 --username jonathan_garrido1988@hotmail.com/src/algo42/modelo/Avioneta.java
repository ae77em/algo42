package algo42.modelo;

public class Avioneta extends NaveArmada {
	
	public Avioneta() {
		posicion = new Punto(0,0);
		tamanio = 1; //este valor es arbitrario
		velocidad = 2; //este valor es arbitrario
		energia = 100; //este valor es arbitrario
		energiaInicial = energia;
		activo = false;
	}


}
