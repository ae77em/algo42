package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoSimple extends Arma {

	public TorpedoSimple(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaTorpedoSimple balaTorpedo = new BalaTorpedoSimple(this.getEquipo());
		return balaTorpedo;
	}
	
	public void persistir(Document doc, Element armas) {
		Element torpedoSimple = doc.createElement("Torpedo Simple");
		armas.appendChild(torpedoSimple);
		
			Element cantidadDeBalas = doc.createElement("Cantidad De Balas");
			torpedoSimple.appendChild(cantidadDeBalas);
			cantidadDeBalas.setTextContent(Integer.toString(this.getCantidadDeBalas()));
		
			Element equipo = doc.createElement("Equipo");
			torpedoSimple.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
	}
	
	public Arma recuperar(Element element, TorpedoSimple torpedoSimple) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Cantidad De Balas")) {
				torpedoSimple.setCantidadDeBalas(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				torpedoSimple.setEquipo(Integer.parseInt(child.getTextContent()));
			}
		}
		return torpedoSimple;
	}
}