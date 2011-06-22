package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class Cohete extends Arma {

	public Cohete(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaCohete balaCohete = new BalaCohete(this.getEquipo());
		return balaCohete;
	}

	public void persistir(Document doc, Element armas) {
		Element cohete = doc.createElement("Cohete");
		armas.appendChild(cohete);
		
			Element cantidadDeBalas = doc.createElement("Cantidad De Balas");
			cohete.appendChild(cantidadDeBalas);
			cantidadDeBalas.setTextContent(Integer.toString(this.getCantidadDeBalas()));
		
			Element equipo = doc.createElement("Equipo");
			cohete.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
	}

	public Arma recuperar(Element element, Cohete cohete) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Cantidad De Balas")) {
				cohete.setCantidadDeBalas(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				cohete.setEquipo(Integer.parseInt(child.getTextContent()));
			}
		}
		return cohete;
	}
}