package algo42.modelo;

import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ZigZag extends EstrategiaArmados {
        
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);	
		this.mover();
		this.disparar();
	}

	public void mover() {
		Punto posicionDeNave = this.getNave().getPosicion();
		int numeroAlAzar = 0;
		if ((posicionDeNave.getY() != 100 )&&(posicionDeNave.getY() != 1)&&(posicionDeNave.getX() != 1)&&(posicionDeNave.getX() != 100)) {
			numeroAlAzar = (int) (Math.random()*4+1);
		}
		if ((posicionDeNave.getY() == 100)||(numeroAlAzar == 1)) {
			this.getNave().setDireccion(new Arriba());
		}
		if ((posicionDeNave.getX() == 100)||(numeroAlAzar == 2)) {
			this.getNave().setDireccion(new Izquierda());
		}
		if ((posicionDeNave.getY() == 1)||(numeroAlAzar == 3)) {
			this.getNave().setDireccion(new Abajo());
		}
		if ((posicionDeNave.getX() == 1)||(numeroAlAzar == 4)) {
			this.getNave().setDireccion(new Derecha());
		}
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}

	public void disparar() {
		Iterator<Arma> iterador = ((NaveArmada) this.getNave()).getArmas().iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.getNave().getPosicion(), this.getTablero(), this.getDireccion());
    	} 
	}
	
	public void persistir(Document doc, Element elemento) {
		Element zigZag = doc.createElement("Zigzag");
		elemento.appendChild(zigZag);
		
			Element direccion = doc.createElement("Direccion");
			zigZag.appendChild(direccion);
			this.getDireccion().persistir(doc, zigZag);
	}
	
	public Estrategia recuperar(Element element, ZigZag zigZag) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					zigZag.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					zigZag.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					zigZag.setDireccion(new Derecha());
				} else {
					zigZag.setDireccion(new Izquierda());
				}
			}
		}
		return zigZag;
	}
}