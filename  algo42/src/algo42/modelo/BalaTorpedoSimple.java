package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BalaTorpedoSimple extends Bala {

	public BalaTorpedoSimple(int unEquipo) {
		super(unEquipo, 7, 10);
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element balaTorpedoSimple = doc.createElement("Bala Torpedo Simple");
		espacioAereo.appendChild(balaTorpedoSimple);
			
			Element posicion = doc.createElement("Posicion");
			balaTorpedoSimple.appendChild(posicion);
			this.getPosicion().persistir(doc, balaTorpedoSimple);
			
			Element direccion = doc.createElement("Direccion");
			balaTorpedoSimple.appendChild(direccion);
			this.getDireccion().persistir(doc, balaTorpedoSimple);
			
			Element velocidad = doc.createElement("Velocidad");
			balaTorpedoSimple.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			balaTorpedoSimple.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			balaTorpedoSimple.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			balaTorpedoSimple.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			balaTorpedoSimple.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element activo = doc.createElement("Activo");
			balaTorpedoSimple.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			balaTorpedoSimple.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
	}
	
	public Movible recuperar(Element element, BalaTorpedoSimple balaTorpedoSimple) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Velocidad")) {
				balaTorpedoSimple.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				balaTorpedoSimple.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				balaTorpedoSimple.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				balaTorpedoSimple.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				balaTorpedoSimple.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					balaTorpedoSimple.setActivo(true);
				} else {
					balaTorpedoSimple.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					balaTorpedoSimple.setExpansible(true);
				} else {
					balaTorpedoSimple.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				balaTorpedoSimple.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					balaTorpedoSimple.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					balaTorpedoSimple.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					balaTorpedoSimple.setDireccion(new Derecha());
				} else {
					balaTorpedoSimple.setDireccion(new Izquierda());
				}
			} 
		}
		return balaTorpedoSimple;
	}
}