package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BalaCohete extends Bala {

	public BalaCohete(int equipo) {
		super(equipo, 6, 30);
	}

	public void persistir(Document doc, Element espacioAereo) {
		Element balaCohete = doc.createElement("Bala Cohete");
		espacioAereo.appendChild(balaCohete);
			
			Element posicion = doc.createElement("Posicion");
			balaCohete.appendChild(posicion);
			this.getPosicion().persistir(doc, balaCohete);
			
			Element direccion = doc.createElement("Direccion");
			balaCohete.appendChild(direccion);
			this.getDireccion().persistir(doc, balaCohete);
			
			Element velocidad = doc.createElement("Velocidad");
			balaCohete.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			balaCohete.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			balaCohete.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			balaCohete.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			balaCohete.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element activo = doc.createElement("Activo");
			balaCohete.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			balaCohete.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
	}
	
	public Movible recuperar(Element element, BalaCohete balaCohete) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Velocidad")) {
				balaCohete.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				balaCohete.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				balaCohete.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				balaCohete.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				balaCohete.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					balaCohete.setActivo(true);
				} else {
					balaCohete.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					balaCohete.setExpansible(true);
				} else {
					balaCohete.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				balaCohete.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					balaCohete.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					balaCohete.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					balaCohete.setDireccion(new Derecha());
				} else {
					balaCohete.setDireccion(new Izquierda());
				}
			} 
		}
		return balaCohete;
	}
}