package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LineaHorizontal extends Estrategia {
        
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);
		Punto posicionDeNave = this.getNave().getPosicion();
		
		if (posicionDeNave.getY() == 100) {
			this.getNave().huir();
		} else {
			this.mover();
		}
	}

	public void mover() {
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}
	
	public void persistir(Document doc, Element elemento) {
		Element lineaHorizontal = doc.createElement("Linea Horizontal");
		elemento.appendChild(lineaHorizontal);
		
			Element direccion = doc.createElement("Direccion");
			lineaHorizontal.appendChild(direccion);
			this.getDireccion().persistir(doc, lineaHorizontal);
	}
	
	public Estrategia recuperar(Element element, LineaHorizontal lineaHorizontal) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					lineaHorizontal.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					lineaHorizontal.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					lineaHorizontal.setDireccion(new Derecha());
				} else {
					lineaHorizontal.setDireccion(new Izquierda());
				}
			}
		}
		return lineaHorizontal;
	}
}