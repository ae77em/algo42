package algo42.modelo;

public class Punto {
	
	private int x;
	private int y;
	
	/*
	 * Constructor
	 *
	 * @param      x  la coordenada x.
	    * @param      y  la coordenada y.
	 */
	
	public Punto(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Getter de la variable X.
	 *
	 */
		
	public int getX(){
		return this.x;
	}
		
	/*
	 * Getter de la variable Y.
	 *
	 */

	public int getY(){
		return this.y;
	}
	
	public void setX(int unPuntoX){
		x = unPuntoX;
	}
	
	public void setY(int unPuntoY){
		y = unPuntoY;
	}
		
	public boolean equals(Punto coordenada){
		if ((coordenada.getX() == this.x) && (coordenada.getY() == this.y))
			return true;
		return false;
	}
	
	public int distancia(Punto posicion){
		int dx = posicion.getX() - this.x;
		int dy = posicion.getY() - this.y;
		
		return (int) Math.sqrt((dx * dx) + (dy * dy));
	}
}