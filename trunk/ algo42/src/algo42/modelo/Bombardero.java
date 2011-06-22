package algo42.modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Bombardero extends NaveArmada {
	
	public Bombardero() {
		super(new ZigZag(), 3, 150, 150, 20);
		try {
			super.agregarArma(new Laser(-1, 2));
			super.agregarArma(new Cohete(-1, 2)); 
			super.agregarArma(new TorpedoRastreador(-1, 2)); 
		} catch (CantidadDeBalasIncorrecta e) {
			// Nunca se llega a tirar esta excepcion
		} 
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
	        	if ((150 - this.getEnergia()) >= cantidadEnergia) {
	                    this.setEnergia(this.getEnergia() + cantidadEnergia);
	            } else {
	            	this.setEnergia(150);
	            }
	        }
		}
	}
	
	public void destruir() {
		this.setEnergia(0);
		this.setActivo(false);
		this.getTablero().aumentarPuntaje(this.getPuntaje());
		this.getTablero().ubicarObjetoEnPosicion(new CajaArmas(), this.getPosicion());
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element bombardero = doc.createElement("Bombardero");
		espacioAereo.appendChild(bombardero);
		
			Element estrategia = doc.createElement("Estrategia");
			bombardero.appendChild(estrategia);
			this.getEstrategia().persistir(doc, bombardero);
			
			Element posicion = doc.createElement("Posicion");
			bombardero.appendChild(posicion);
			this.getPosicion().persistir(doc, bombardero);
			
			Element direccion = doc.createElement("Direccion");
			bombardero.appendChild(direccion);
			this.getDireccion().persistir(doc, bombardero);
			
			Element velocidad = doc.createElement("Velocidad");
			bombardero.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			bombardero.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			bombardero.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			bombardero.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			bombardero.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element puntaje = doc.createElement("Puntaje");
			bombardero.appendChild(puntaje);
			puntaje.setTextContent(Integer.toString(this.getPuntaje()));
			
			Element activo = doc.createElement("Activo");
			bombardero.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			bombardero.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
		
			Element armas = doc.createElement("Armas");
			bombardero.appendChild(armas);
			for (int i = 0; i < (this.getArmas().size()); i ++) {
				this.getArmas().get(i).persistir(doc, armas);
			}		
	}
	
	public Movible recuperar(Element element, Bombardero bombardero) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Estrategia")) {
				if (child.getTextContent().equals("Circulo")) {
					Circulo circulo = new Circulo();
					bombardero.setEstrategia(circulo.recuperar(element, circulo));
				} else if (child.getTextContent().equals("Ida y Vuelta")) {
					IdaVuelta idaVuelta = new IdaVuelta();
					bombardero.setEstrategia(idaVuelta.recuperar(element, idaVuelta));
				} else if (child.getTextContent().equals("Linea Horizontal")) {
					LineaHorizontal lineaHorizontal = new LineaHorizontal();
					bombardero.setEstrategia(lineaHorizontal.recuperar(element, lineaHorizontal));
				} else if (child.getTextContent().equals("Zigzag")) {
					ZigZag zigZag = new ZigZag();
					bombardero.setEstrategia(zigZag.recuperar(element, zigZag));
				}
			} else if (child.getNodeName().equals("Velocidad")) {
				bombardero.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				bombardero.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				bombardero.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				bombardero.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				bombardero.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Puntaje")) {
				bombardero.setPuntaje(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					bombardero.setActivo(true);
				} else {
					bombardero.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					bombardero.setExpansible(true);
				} else {
					bombardero.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				bombardero.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					bombardero.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					bombardero.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					bombardero.setDireccion(new Derecha());
				} else {
					bombardero.setDireccion(new Izquierda());
				}
			} else if (child.getNodeName().equals("Armas")) {
				ArrayList<Arma> armas = new ArrayList<Arma>();
				NodeList childs2 = element.getChildNodes();
				for (int j = 0; i < childs2.getLength(); i++) {
					Node child2 = childs2.item(j);
					try {
						if (child2.getTextContent().equals("Cohete")) {
							Cohete cohete = new Cohete(-2, -2);
							armas.add(cohete.recuperar(element, cohete));
						} else if (child2.getTextContent().equals("Laser")) {
							Laser laser = new Laser(-2, -2);
							armas.add(laser.recuperar(element, laser));
						} else if (child2.getTextContent().equals("Torpedo Adaptable")) {
							TorpedoAdaptable torpedoAdaptable = new TorpedoAdaptable(-2, -2);
							armas.add(torpedoAdaptable.recuperar(element, torpedoAdaptable));
						} else if (child2.getTextContent().equals("Torpedo Rastreador")) {
							TorpedoRastreador torpedoRastreador = new TorpedoRastreador(-2, -2);
							armas.add(torpedoRastreador.recuperar(element, torpedoRastreador));
						} else if (child2.getTextContent().equals("Torpedo Simple")) {
							TorpedoSimple torpedoSimple = new TorpedoSimple(-2, -2);
							armas.add(torpedoSimple.recuperar(element, torpedoSimple));
						}
					} catch (CantidadDeBalasIncorrecta e) {
						// Nunca se llega a tirar esta excepcion
					}
				}
			}
		}
		return bombardero;
	}
}