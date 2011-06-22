package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BalaTorpedoRastreador extends Bala {

	private int contadorDeTiempo;
	
	public BalaTorpedoRastreador(int unEquipo) {
		super(unEquipo, 5, 20);
		this.contadorDeTiempo = 0;
	}
	
	public void mover() {
		Punto posicionDelJugador = this.getTablero().getPosicionDelJugador();
		if (this.contadorDeTiempo == 20) {
			this.destruir();
		} else {
			this.contadorDeTiempo = this.contadorDeTiempo + 1;
		}
		if (this.getActivo() == true) {
			if (this.getPosicion().getX() > posicionDelJugador.getX()) {
				this.setDireccion(new Izquierda());
			}
			if (this.getPosicion().getX() < posicionDelJugador.getX()) {
				this.setDireccion(new Derecha());
			}
			if (this.getPosicion().getY() > posicionDelJugador.getY()) {
				this.setDireccion(new Arriba());
			}
			if (this.getPosicion().getY() < posicionDelJugador.getY()) {
				this.setDireccion(new Abajo());
			}
		}
		this.getDireccion().trasladar(this, this.getTablero());
		if ((this.getPosicion().getX() > 100)||(this.getPosicion().getX() < 1)||(this.getPosicion().getY() > 100)||(this.getPosicion().getY() < 1)) {
			this.destruir();
		}
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element balaTorpedoRastreador = doc.createElement("Bala Torpedo Rastreador");
		espacioAereo.appendChild(balaTorpedoRastreador);
			
			Element posicion = doc.createElement("Posicion");
			balaTorpedoRastreador.appendChild(posicion);
			this.getPosicion().persistir(doc, balaTorpedoRastreador);
			
			Element direccion = doc.createElement("Direccion");
			balaTorpedoRastreador.appendChild(direccion);
			this.getDireccion().persistir(doc, balaTorpedoRastreador);
			
			Element velocidad = doc.createElement("Velocidad");
			balaTorpedoRastreador.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			balaTorpedoRastreador.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			balaTorpedoRastreador.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			balaTorpedoRastreador.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			balaTorpedoRastreador.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element activo = doc.createElement("Activo");
			balaTorpedoRastreador.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			balaTorpedoRastreador.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
			
			Element contadorDeTiempo = doc.createElement("Contador De Tiempo");
			balaTorpedoRastreador.appendChild(contadorDeTiempo);
			contadorDeTiempo.setTextContent(Integer.toString(this.contadorDeTiempo));
	}
	
	public Movible recuperar(Element element, BalaTorpedoRastreador balaTorpedoRastreador) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Velocidad")) {
				balaTorpedoRastreador.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				balaTorpedoRastreador.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				balaTorpedoRastreador.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				balaTorpedoRastreador.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				balaTorpedoRastreador.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					balaTorpedoRastreador.setActivo(true);
				} else {
					balaTorpedoRastreador.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					balaTorpedoRastreador.setExpansible(true);
				} else {
					balaTorpedoRastreador.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				balaTorpedoRastreador.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					balaTorpedoRastreador.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					balaTorpedoRastreador.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					balaTorpedoRastreador.setDireccion(new Derecha());
				} else {
					balaTorpedoRastreador.setDireccion(new Izquierda());
				}
			} else if (child.getNodeName().equals("Contador De Tiempo")) {
				balaTorpedoRastreador.contadorDeTiempo = Integer.parseInt(child.getTextContent());
			}
		}
		return balaTorpedoRastreador;
	}
}