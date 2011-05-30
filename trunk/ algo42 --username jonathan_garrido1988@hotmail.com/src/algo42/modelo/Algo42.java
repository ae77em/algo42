package algo42.modelo;

import java.util.ArrayList;
import algo42.modelo.excepciones.*;


public class Algo42 implements Movible {
	private ArrayList<Arma> armas;
	private int velocidad, energia, equipo, danio, tamanio;
	private boolean activo, ocupado, expansible;
	private Punto posicion;
	private Direccion direccion;
	private Mision tablero;
	private Algo42 centroDeObjeto;

	
	public Algo42(){
		Laser laser = null;
		try {
			laser = new Laser(-1, 1);
		} catch (CantidadDeBalasIncorrecta e) {
		}
		this.armas = new ArrayList<Arma>();
		
		this.armas.add(laser);
		this.velocidad = 2;
		this.energia = 1000;
		this.activo = false;
		this.ocupado = false;
		this.equipo = 1;
		this.expansible = true;
		this.danio = 1000;
		this.direccion = null;
		this.tamanio = 1;
		this.centroDeObjeto = this;
		this.posicion = new Punto(0, 0);
	}
	
	public Punto getPosicion() {
		return this.posicion;
	}
	
	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}
	
	public boolean getocupado(){
		return this.ocupado;
	}
	
	public void setOcupado(boolean valor){
		this.ocupado = valor;
	}

	public boolean getActivo(){
		return this.activo;
	}
	
	public void setActivo(boolean valor){
		this.activo = valor;
	}
	
	public ArrayList<Arma> getArmas(){
		return this.armas;
	}
	
	public Algo42 getCentroDeObjeto(){
		return this.centroDeObjeto;
	}
	
	public void setCentroDeObjeto(Algo42 objeto){
		this.centroDeObjeto = objeto;
	}
	
	public int getDanio(){
		return this.danio;
	}
	
	public int getEnergia(){
		return this.energia;
	}
	
	public int getEquipo(){
		return this.equipo;
	}
	
	public boolean getExpansible(){
		return this.expansible;
	}
	
	public int getVelocidad(){
		return this.velocidad;
	}
	
	public int getTamanio(){
		return this.tamanio;
	}
	
	public void activar(Mision mision, Punto puntoInicialJugador) {
		this.activo = true;
		this.ocupado = true;
		this.tablero = mision;
		this.posicion = puntoInicialJugador;
		this.direccion = new Arriba();
	}

	public void volar(int unaDireccion) {
		if (unaDireccion == 2){
			this.direccion = new Izquierda();
		}
		else if (unaDireccion == 3){
			this.direccion = new Abajo();
		}
		else{
			this.direccion = new Derecha();
		}
		this.direccion.trasladar(this, this.tablero);
	}

	public void cargar(Arma arma) {
		this.armas.add(arma);
	}
	
	public void destruir(){
		this.energia = 0;
		try {
			this.tablero.hayAlguien(this.posicion);
		} catch (CoordenadaFueraDeRangoError e) {
			e.printStackTrace();
		}
		this.activo = false;
		this.ocupado = false;
		this.tablero.misionPerdida();
	}
	
	public void disparar(){
		for (int i = 0; i < this.armas.size(); i++){
			this.armas.get(i).disparar(this.posicion, this.tablero, this.direccion);
		}
	}

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (cantidadEnergia <= 0){
			throw new CantidadDeEnergiaIncorrecta();
		}
		else if ((1000 - this.energia) >= cantidadEnergia){
			this.energia = this.energia + cantidadEnergia;
		}
		else{
			this.energia = 1000;
		}
	}
	
	public void disminuirEnergia (int unDanio){
		if ((energia - unDanio) <= 0){
			this.destruir();
		}
		else{
			this.energia = this.energia - unDanio;
		}
	}

	public void mover() {
	}
}
