package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class Laser extends Arma {
	
	public Laser(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaLaser balaLaser = new BalaLaser(this.getEquipo());
		return balaLaser;
	}
	
	public void persistir(Document doc, Element armas) {
		Element laser = doc.createElement("Laser");
		armas.appendChild(laser);
		
			Element cantidadDeBalas = doc.createElement("Cantidad De Balas");
			laser.appendChild(cantidadDeBalas);
			cantidadDeBalas.setTextContent(Integer.toString(this.getCantidadDeBalas()));
		
			Element equipo = doc.createElement("Equipo");
			laser.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
	}
	
	public Arma recuperar(Element element, Laser laser) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Cantidad De Balas")) {
				laser.setCantidadDeBalas(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				laser.setEquipo(Integer.parseInt(child.getTextContent()));
			}
		}
		return laser;
	}
}