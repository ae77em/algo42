package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class CajaEnergia extends Objeto {

	private int cantidad;
	
	public CajaEnergia() {
		super();
		this.cantidad = 50;
	}
	
	public void consumirPor(Algo42 algo42) {
		if (this.getActivo() == true) {
			try {
				algo42.aumentarEnergia(this.cantidad);
			} catch (CantidadDeEnergiaIncorrecta e) {
				e.printStackTrace();
			}
			this.destruir();
		}
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element cajaEnergia = doc.createElement("Caja Energia");
		espacioAereo.appendChild(cajaEnergia);
			
			Element posicion = doc.createElement("Posicion");
			cajaEnergia.appendChild(posicion);
			this.getPosicion().persistir(doc, cajaEnergia);
			
			Element direccion = doc.createElement("Direccion");
			cajaEnergia.appendChild(direccion);
			this.getDireccion().persistir(doc, cajaEnergia);
			
			Element velocidad = doc.createElement("Velocidad");
			cajaEnergia.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			cajaEnergia.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			cajaEnergia.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			cajaEnergia.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			cajaEnergia.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element activo = doc.createElement("Activo");
			cajaEnergia.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			cajaEnergia.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
			
			Element cantidad = doc.createElement("Cantidad");
			cajaEnergia.appendChild(cantidad);
			cantidad.setTextContent(Integer.toString(this.cantidad));
	}
	
	public Movible recuperar(Element element, CajaEnergia cajaEnergia) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Velocidad")) {
				cajaEnergia.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				cajaEnergia.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				cajaEnergia.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				cajaEnergia.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				cajaEnergia.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					cajaEnergia.setActivo(true);
				} else {
					cajaEnergia.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					cajaEnergia.setExpansible(true);
				} else {
					cajaEnergia.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				cajaEnergia.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					cajaEnergia.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					cajaEnergia.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					cajaEnergia.setDireccion(new Derecha());
				} else {
					cajaEnergia.setDireccion(new Izquierda());
				}
			} else if (child.getNodeName().equals("Cantidad")) {
				cajaEnergia.cantidad = Integer.parseInt(child.getTextContent());
			}
		}
		return cajaEnergia;
	}
}