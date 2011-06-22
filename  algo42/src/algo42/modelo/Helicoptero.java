package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Helicoptero extends Nave {
        
	public Helicoptero() {
		super(new Circulo(), 1, 50, 50, -200);
	}

	public void activar(Mision tablero, Punto posicion) {
		this.setActivo(true);
        this.setTablero(tablero);
        this.setPosicion(posicion);
	}

	public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
		if (this.getActivo() == true) {
			if (cantidadEnergia <= 0) {
	    		throw new CantidadDeEnergiaIncorrecta();
	        } else {
	        	if ((50 - this.getEnergia()) >= cantidadEnergia) {
	                    this.setEnergia(this.getEnergia() + cantidadEnergia);
	            } else {
	            	this.setEnergia(50);
	            }
	        }
		}
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element helicoptero = doc.createElement("Helicoptero");
		espacioAereo.appendChild(helicoptero);
		
			Element estrategia = doc.createElement("Estrategia");
			helicoptero.appendChild(estrategia);
			this.getEstrategia().persistir(doc, helicoptero);
			
			Element posicion = doc.createElement("Posicion");
			helicoptero.appendChild(posicion);
			this.getPosicion().persistir(doc, helicoptero);
			
			Element direccion = doc.createElement("Direccion");
			helicoptero.appendChild(direccion);
			this.getDireccion().persistir(doc, helicoptero);
			
			Element velocidad = doc.createElement("Velocidad");
			helicoptero.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			helicoptero.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			helicoptero.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			helicoptero.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			helicoptero.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element puntaje = doc.createElement("Puntaje");
			helicoptero.appendChild(puntaje);
			puntaje.setTextContent(Integer.toString(this.getPuntaje()));
			
			Element activo = doc.createElement("Activo");
			helicoptero.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			helicoptero.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}	
	}
	
	public Movible recuperar(Element element, Helicoptero helicoptero) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Estrategia")) {
				if (child.getTextContent().equals("Circulo")) {
					Circulo circulo = new Circulo();
					helicoptero.setEstrategia(circulo.recuperar(element, circulo));
				} else if (child.getTextContent().equals("Ida y Vuelta")) {
					IdaVuelta idaVuelta = new IdaVuelta();
					helicoptero.setEstrategia(idaVuelta.recuperar(element, idaVuelta));
				} else if (child.getTextContent().equals("Linea Horizontal")) {
					LineaHorizontal lineaHorizontal = new LineaHorizontal();
					helicoptero.setEstrategia(lineaHorizontal.recuperar(element, lineaHorizontal));
				} else if (child.getTextContent().equals("Zigzag")) {
					ZigZag zigZag = new ZigZag();
					helicoptero.setEstrategia(zigZag.recuperar(element, zigZag));
				}
			} else if (child.getNodeName().equals("Velocidad")) {
				helicoptero.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				helicoptero.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				helicoptero.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				helicoptero.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				helicoptero.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Puntaje")) {
				helicoptero.setPuntaje(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					helicoptero.setActivo(true);
				} else {
					helicoptero.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					helicoptero.setExpansible(true);
				} else {
					helicoptero.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				helicoptero.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					helicoptero.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					helicoptero.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					helicoptero.setDireccion(new Derecha());
				} else {
					helicoptero.setDireccion(new Izquierda());
				}
			}
		}
		return helicoptero;
	}
}