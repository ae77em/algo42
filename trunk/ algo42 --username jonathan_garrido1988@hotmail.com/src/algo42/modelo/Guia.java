package algo42.modelo;

public class Guia extends NaveArmada {
	
	Guia(){
		posicion = new Punto(0,0);
		tamanio = 1.5; //este valor es arbitrario
		velocidad = 1.5; //este valor es arbitrario
		energia = 100; //este valor es arbitrario
		energiaInicial = energia;
		activo = false;
	}

}
