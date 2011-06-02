package algo42.modelo;

public class Punto {
	
	private int x;
	private int y;
	
	public Punto(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
		
	public boolean equals(Punto coordenada){
		if ((coordenada.getX() == this.x) && (coordenada.getY() == this.y)) {
			return true;
		}
		return false;
	}
	
	public int distancia(Punto posicion){
		int dx = posicion.getX() - this.x;
		int dy = posicion.getY() - this.y;
		
		return (int) Math.sqrt((dx * dx) + (dy * dy));
	}
}