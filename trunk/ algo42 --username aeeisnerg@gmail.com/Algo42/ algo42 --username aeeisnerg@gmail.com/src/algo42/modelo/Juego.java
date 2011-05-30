package algo42.modelo;

public class Juego {
	
	private Mision mision;
	private int contadorDeMision;
	private int ganaste;
	
	public Juego(){
		this.mision = new Mision();
		this.contadorDeMision = 1;
		this.ganaste = 0;
	}
	
	public int getContadorDeMision(){
		return this.contadorDeMision;
	}
	
	public int getGanaste(){
		return this.ganaste;
	}
	
	public void comenzar (){
		while (this.mision.getActivo() == false){
			if (this.contadorDeMision > 10){
				this.ganaste();
			} 
			else {
				this.mision = new Mision();
				this.contadorDeMision = this.contadorDeMision + 1;
				this.mision.inicializarMisionEnJuego (this.contadorDeMision, this);
				this.mision.comenzar();
			}
		}
	}

	public void ganaste() {
		//GANASTE
		
		this.ganaste = 1;
	}
	
	public void perdiste() {
		//PERDISTE
		
		this.ganaste = -1;
	}
	
	public void aumentarContadorDeMision(){
		this.contadorDeMision = this.contadorDeMision + 1;
	}
}
