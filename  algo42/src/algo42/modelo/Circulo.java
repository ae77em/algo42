package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Circulo extends Estrategia {
	
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);
		Punto posicionDeNave = this.getNave().getPosicion();
        
		if ((posicionDeNave.getX() > 21*5)&&(posicionDeNave.getX() <= 51*5)&&(posicionDeNave.getY() >= 2*5)&&(posicionDeNave.getY() < 17*5)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
		}
		if ((posicionDeNave.getX() > 6*5)&&(posicionDeNave.getX() <= 21*5)&&(posicionDeNave.getY() >= 17*5)&&(posicionDeNave.getY() < 47*5)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() >= 6*5)&&(posicionDeNave.getX() < 21*5)&&(posicionDeNave.getY() >= 47*5)&&(posicionDeNave.getY() < 77*5)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() >= 21*5)&&(posicionDeNave.getX() < 51*5)&&(posicionDeNave.getY() >= 77*5)&&(posicionDeNave.getY() < 92*5)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Abajo());
			this.mover();
		}
		if ((posicionDeNave.getX() >= 51*5)&&(posicionDeNave.getX() < 81*5)&&(posicionDeNave.getY() > 77*5)&&(posicionDeNave.getY() <= 92*5)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
		}
		if ((posicionDeNave.getX() >= 81*5)&&(posicionDeNave.getX() < 96*5)&&(posicionDeNave.getY() > 47*5)&&(posicionDeNave.getY() <= 81*5)) {
			this.getNave().setDireccion(new Derecha());
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() > 81*5)&&(posicionDeNave.getX() <= 96*5)&&(posicionDeNave.getY() > 17*5)&&(posicionDeNave.getY() <= 47*5)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
			this.mover();
		}
		if ((posicionDeNave.getX() > 51*5)&&(posicionDeNave.getX() <= 81*5)&&(posicionDeNave.getY() > 2*5)&&(posicionDeNave.getY() <= 17*5)) {
			this.getNave().setDireccion(new Izquierda());
			this.mover();
			this.mover();
			this.getNave().setDireccion(new Arriba());
			this.mover();
		}
	}

	public void mover() {
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}

	public void persistir(Document doc, Element elemento) {
		Element circulo = doc.createElement("Circulo");
		elemento.appendChild(circulo);
		
			Element direccion = doc.createElement("Direccion");
			circulo.appendChild(direccion);
			this.getDireccion().persistir(doc, circulo);
	}
	
	public Estrategia recuperar(Element element, Circulo circulo) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					circulo.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					circulo.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					circulo.setDireccion(new Derecha());
				} else {
					circulo.setDireccion(new Izquierda());
				}
			}
		}
		return circulo;
	}
}