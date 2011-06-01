package algo42.modelo;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Nave implements Movible {
	
	private Estrategia estrategia;
	private Punto posicion;
	private float tamanio;
	private float energia;
	private float energiaInicial;
	private float velocidad;
	private boolean activo;
	
	
	Nave(){
		posicion = new Punto(0,0);
		tamanio = 1; //este valor es arbitrario
		velocidad = 1; //este valor es arbitrario
		energia = 50; //este valor es arbitrario
		energiaInicial = energia;
		activo = false;
	}

	public void activar(Mision tablero, Punto posicion) {
		activo = true;
			
	}
	
	public void establecerEstrategia(Estrategia suEstrategia){
		estrategia = suEstrategia;
	}

	public void actuar() {
		// TODO Auto-generated method stub
		
	}

	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto otraPosicion) {
		posicion = otraPosicion;
	}

	public float getTamanio() {
		return tamanio;
	}
	
	public void verificarCantidadDeEnergia(float cantidad) throws Exception{
		if ( cantidad < 0) throws new CantidadDeEnergiaIncorrecta();
	}

	public void aumentarEnergia(float cantidad){ 
		try { verificarCantidadDeEnergia(cantidad);
				energia = energia + cantidad;
		} catch (CantidadDeEnergiaIncorrecta e) {
			System.err.println("Error " + e);
		}			
	}		

	public void disminuirEnergia(float cantidad) {
		try { verificarCantidadDeEnergia(cantidad);
		energia = energia - cantidad;
		} catch (CantidadDeEnergiaIncorrecta e) {
			System.err.println("Error " + e);
		}
	}

	public int getVelocidad() {
		return velocidad;
	}

	public boolean getActivo() {
		return activo;
	}

	public int getEquipo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean getExpansible() {
		// TODO Auto-generated method stub
		return false;
	}

	public float getDanio() {
		return ( energiaInicial - energia );
	}

	public float getEnergia() {
		return energia;
	}

}
