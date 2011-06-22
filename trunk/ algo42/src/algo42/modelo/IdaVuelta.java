package algo42.modelo;

import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IdaVuelta extends EstrategiaArmados {
        
	private boolean vueltaYaHecha = false;
	
	public void usar(Nave nave, Mision tablero) {
		this.setTablero(tablero);
		this.setNave(nave);
		Punto posicionDeNave = this.getNave().getPosicion();
		
		if ((posicionDeNave.getY() == 1)&&(this.vueltaYaHecha == true)) {
			this.getNave().huir();
		}
		if (posicionDeNave.getY() == 100) {
			this.girar();
			this.mover();
		} else {
			this.mover();
			this.disparar();
		}
	}

	public void mover() {
		this.setDireccion(this.getNave().getDireccion());
		this.getDireccion().trasladar(this.getNave(), this.getTablero());
	}

	public void disparar() {
		Iterator<Arma> iterador = ((NaveArmada) this.getNave()).getArmas().iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.getNave().getPosicion(), this.getTablero(), this.getDireccion());
    	} 
	}
	
	public void girar() {
		this.getNave().setDireccion(new Arriba());
		this.vueltaYaHecha = true;
	}
	
	public void persistir(Document doc, Element elemento) {
		Element idaVuelta = doc.createElement("Ida Y Vuelta");
		elemento.appendChild(idaVuelta);
		
			Element direccion = doc.createElement("Direccion");
			idaVuelta.appendChild(direccion);
			this.getDireccion().persistir(doc, idaVuelta);
			
			Element vueltaYaHecha = doc.createElement("Vuelta Ya Hecha");
			idaVuelta.appendChild(vueltaYaHecha);
			if (this.vueltaYaHecha == true) {
				vueltaYaHecha.setTextContent("True");
			} else {
				vueltaYaHecha.setTextContent("False");
			}
	}
	
	public Estrategia recuperar(Element element, IdaVuelta idaVuelta) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					idaVuelta.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					idaVuelta.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					idaVuelta.setDireccion(new Derecha());
				} else {
					idaVuelta.setDireccion(new Izquierda());
				}
			} else if (child.getNodeName().equals("Vuelta Ya Hecha")) {
				if (child.getTextContent() == "True") {
					idaVuelta.vueltaYaHecha = true;
				} else {
					idaVuelta.vueltaYaHecha = false;
				}
			}
		}
		return idaVuelta;
	}
}