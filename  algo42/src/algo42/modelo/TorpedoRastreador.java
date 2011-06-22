package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoRastreador extends Arma {

	public TorpedoRastreador(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
	}
	
	public Bala getBala() {
		BalaLaser balaLaser = new BalaLaser(this.getEquipo());
		return balaLaser;
	}
	
	public void persistir(Document doc, Element armas) {
		Element torpedoRastreador = doc.createElement("Torpedo Rastreador");
		armas.appendChild(torpedoRastreador);
		
			Element cantidadDeBalas = doc.createElement("Cantidad De Balas");
			torpedoRastreador.appendChild(cantidadDeBalas);
			cantidadDeBalas.setTextContent(Integer.toString(this.getCantidadDeBalas()));
		
			Element equipo = doc.createElement("Equipo");
			torpedoRastreador.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
	}
	
	public Arma recuperar(Element element, TorpedoRastreador torpedoRastreador) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Cantidad De Balas")) {
				torpedoRastreador.setCantidadDeBalas(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				torpedoRastreador.setEquipo(Integer.parseInt(child.getTextContent()));
			}
		}
		return torpedoRastreador;
	}
}