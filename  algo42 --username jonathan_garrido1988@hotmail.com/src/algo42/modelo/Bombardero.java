package algo42.modelo;

public class Bombardero extends NaveArmada {
	
	public Bombardero() {
		posicion = new Punto(0,0);
		tamanio = 1; //este valor es arbitrario
		velocidad = 1; //este valor es arbitrario
		energia = 50; //este valor es arbitrario
		energiaInicial = energia;
		activo = false;
	}
}
