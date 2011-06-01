package algo42.modelo;

public class Explorador extends Nave {
	
	Explorador(){
		posicion = new Punto(0,0);
		tamanio = 1; //este valor es arbitrario
		velocidad = 0.5; //este valor es arbitrario
		energia = 100; //este valor es arbitrario
		energiaInicial = energia;
		activo = false;
	}
	
}

