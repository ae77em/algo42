package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Civil extends Nave {
        
	public Civil() {
		super(new LineaHorizontal(), 1, 50, 50, -300);
	}

	public void activar(Mision tablero, Punto posicion) {	
		this.setActivo(true);
        this.setTablero(tablero);
        this.setPosicion(posicion);
        this.setDireccion(new Derecha());
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
		Element civil = doc.createElement("Civil");
		espacioAereo.appendChild(civil);
		
			Element estrategia = doc.createElement("Estrategia");
			civil.appendChild(estrategia);
			this.getEstrategia().persistir(doc, civil);
			
			Element posicion = doc.createElement("Posicion");
			civil.appendChild(posicion);
			this.getPosicion().persistir(doc, civil);
			
			Element direccion = doc.createElement("Direccion");
			civil.appendChild(direccion);
			this.getDireccion().persistir(doc, civil);
			
			Element velocidad = doc.createElement("Velocidad");
			civil.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			civil.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			civil.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			civil.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			civil.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element puntaje = doc.createElement("Puntaje");
			civil.appendChild(puntaje);
			puntaje.setTextContent(Integer.toString(this.getPuntaje()));
			
			Element activo = doc.createElement("Activo");
			civil.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			civil.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}		
	}
	
	public Movible recuperar(Element element, Civil civil) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Estrategia")) {
				if (child.getTextContent().equals("Circulo")) {
					Circulo circulo = new Circulo();
					civil.setEstrategia(circulo.recuperar(element, circulo));
				} else if (child.getTextContent().equals("Ida y Vuelta")) {
					IdaVuelta idaVuelta = new IdaVuelta();
					civil.setEstrategia(idaVuelta.recuperar(element, idaVuelta));
				} else if (child.getTextContent().equals("Linea Horizontal")) {
					LineaHorizontal lineaHorizontal = new LineaHorizontal();
					civil.setEstrategia(lineaHorizontal.recuperar(element, lineaHorizontal));
				} else if (child.getTextContent().equals("Zigzag")) {
					ZigZag zigZag = new ZigZag();
					civil.setEstrategia(zigZag.recuperar(element, zigZag));
				}
			} else if (child.getNodeName().equals("Velocidad")) {
				civil.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				civil.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				civil.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				civil.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				civil.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Puntaje")) {
				civil.setPuntaje(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					civil.setActivo(true);
				} else {
					civil.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					civil.setExpansible(true);
				} else {
					civil.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				civil.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					civil.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					civil.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					civil.setDireccion(new Derecha());
				} else {
					civil.setDireccion(new Izquierda());
				}
			}
		}
		return civil;
	}
}