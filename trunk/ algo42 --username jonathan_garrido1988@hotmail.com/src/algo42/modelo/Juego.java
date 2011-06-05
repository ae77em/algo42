package algo42.modelo;

public class Juego {
	
	private Mision mision;
	private int ganaste;
	
	public Juego() {
		this.ganaste = 0;
	}
	
	public void comenzar() {
		this.mision = new Mision(this);
		this.mision.comenzar();
	}

	public void ganaste() {
		this.ganaste = 1;	/*GANASTE*/
	}
	
	public void perdiste() {
		this.ganaste = -1;	/*PERDISTE*/
	}
	
	public int getGanaste() {
		return this.ganaste;
	}
}