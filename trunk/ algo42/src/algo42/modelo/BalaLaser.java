package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BalaLaser extends Bala {
	
	public BalaLaser(int unEquipo) {
		super(unEquipo, 7, 10);
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element balaLaser = doc.createElement("Bala Laser");
		espacioAereo.appendChild(balaLaser);
			
			Element posicion = doc.createElement("Posicion");
			balaLaser.appendChild(posicion);
			this.getPosicion().persistir(doc, balaLaser);
			
			Element direccion = doc.createElement("Direccion");
			balaLaser.appendChild(direccion);
			this.getDireccion().persistir(doc, balaLaser);
			
			Element velocidad = doc.createElement("Velocidad");
			balaLaser.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			balaLaser.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			balaLaser.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			balaLaser.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			balaLaser.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element activo = doc.createElement("Activo");
			balaLaser.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			balaLaser.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
	}
	
	public Movible recuperar(Element element, BalaLaser balaLaser) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Velocidad")) {
				balaLaser.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				balaLaser.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				balaLaser.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				balaLaser.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				balaLaser.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					balaLaser.setActivo(true);
				} else {
					balaLaser.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					balaLaser.setExpansible(true);
				} else {
					balaLaser.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				balaLaser.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					balaLaser.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					balaLaser.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					balaLaser.setDireccion(new Derecha());
				} else {
					balaLaser.setDireccion(new Izquierda());
				}
			} 
		}
		return balaLaser;
	}
}