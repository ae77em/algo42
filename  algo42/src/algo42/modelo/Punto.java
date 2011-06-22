package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Punto {
	
	private int x, y;
	
	public Punto(int x, int y){
		this.setX(x);
		this.setY(y);
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
	
	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
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

	public void persistir(Document doc, Element elemento) {
		elemento.setTextContent(Integer.toString(this.x)+"@"+Integer.toString(this.y));
	}

	public Punto recuperar(Element element, Punto posicion) {
		NodeList childs = element.getChildNodes();
		Node child = childs.item(0);
		String x = "";
		String y = "";
		boolean xObtenida = false;
		for (int i = 0; i < child.getTextContent().length(); i++) {
			if (child.getTextContent().charAt(i) != '@') {
				if (xObtenida == false) {
					x = x + Character.toString(child.getTextContent().charAt(i));
				} else {
					y = y + Character.toString(child.getTextContent().charAt(i));
				}
			} else {
				xObtenida = true;
			}
		}
		posicion.setX(Integer.parseInt(x));
		posicion.setY(Integer.parseInt(y));
		return posicion;
	}
}