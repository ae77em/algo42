package algo42.modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Avioneta extends NaveArmada {
        
	public Avioneta() {
		super(new IdaVuelta(), 3, 150, 150, 20);
		try {
			super.agregarArma(new Laser(-1, 2));
		} catch (CantidadDeBalasIncorrecta e) {
			// Nunca se llega a tirar esta excepcion
		}
	}
	
	public void activar(Mision tablero, Punto posicion) {
		this.setActivo(true);
        this.setTablero(tablero);
        this.setPosicion(posicion);
    	this.setDireccion(new Abajo());
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
	
	public void persistir(Document doc, Element espacioAereo) {
		Element avioneta = doc.createElement("Avioneta");
		espacioAereo.appendChild(avioneta);
		
			Element estrategia = doc.createElement("Estrategia");
			avioneta.appendChild(estrategia);
			this.getEstrategia().persistir(doc, avioneta);
			
			Element posicion = doc.createElement("Posicion");
			avioneta.appendChild(posicion);
			this.getPosicion().persistir(doc, avioneta);
			
			Element direccion = doc.createElement("Direccion");
			avioneta.appendChild(direccion);
			this.getDireccion().persistir(doc, avioneta);
			
			Element velocidad = doc.createElement("Velocidad");
			avioneta.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			avioneta.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			avioneta.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			avioneta.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			avioneta.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element puntaje = doc.createElement("Puntaje");
			avioneta.appendChild(puntaje);
			puntaje.setTextContent(Integer.toString(this.getPuntaje()));
			
			Element activo = doc.createElement("Activo");
			avioneta.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			avioneta.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
		
			Element armas = doc.createElement("Armas");
			avioneta.appendChild(armas);
			for (int i = 0; i < (this.getArmas().size()); i ++) {
				this.getArmas().get(i).persistir(doc, armas);
			}		
	}
	
	public Movible recuperar(Element element, Avioneta avioneta) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Estrategia")) {
				if (child.getTextContent().equals("Circulo")) {
					Circulo circulo = new Circulo();
					avioneta.setEstrategia(circulo.recuperar(element, circulo));
				} else if (child.getTextContent().equals("Ida y Vuelta")) {
					IdaVuelta idaVuelta = new IdaVuelta();
					avioneta.setEstrategia(idaVuelta.recuperar(element, idaVuelta));
				} else if (child.getTextContent().equals("Linea Horizontal")) {
					LineaHorizontal lineaHorizontal = new LineaHorizontal();
					avioneta.setEstrategia(lineaHorizontal.recuperar(element, lineaHorizontal));
				} else if (child.getTextContent().equals("Zigzag")) {
					ZigZag zigZag = new ZigZag();
					avioneta.setEstrategia(zigZag.recuperar(element, zigZag));
				}
			} else if (child.getNodeName().equals("Velocidad")) {
				avioneta.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				avioneta.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				avioneta.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				avioneta.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				avioneta.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Puntaje")) {
				avioneta.setPuntaje(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					avioneta.setActivo(true);
				} else {
					avioneta.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					avioneta.setExpansible(true);
				} else {
					avioneta.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				avioneta.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					avioneta.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					avioneta.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					avioneta.setDireccion(new Derecha());
				} else {
					avioneta.setDireccion(new Izquierda());
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
		return avioneta;
	}
}