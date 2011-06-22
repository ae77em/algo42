package algo42.modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;
import algo42.modelo.excepciones.CantidadDeEnergiaIncorrecta;

public class Caza extends NaveArmada {
        
	public Caza() {
		super(new IdaVuelta(), 2, 250, 250, 30);
		int numeroAlAzar = (int) (Math.random()*2+1);
		try {
			if (numeroAlAzar == 1) {
				super.agregarArma(new TorpedoSimple(-1, 2));
			} else {
				Arma arma = new TorpedoAdaptable(-1, 2);
				super.agregarArma(arma);
				arma.setNave(this);
			}
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
	        	if ((250 - this.getEnergia()) >= cantidadEnergia) {
	                    this.setEnergia(this.getEnergia() + cantidadEnergia);
	            } else {
	            	this.setEnergia(250);
	            }
	        }
		}
	}
	
	public void destruir() {
		this.setEnergia(0);
		this.setActivo(false);
		this.getTablero().aumentarPuntaje(this.getPuntaje());
		this.getTablero().ubicarObjetoEnPosicion(new CajaEnergia(), this.getPosicion());
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element caza = doc.createElement("Caza");
		espacioAereo.appendChild(caza);
		
			Element estrategia = doc.createElement("Estrategia");
			caza.appendChild(estrategia);
			this.getEstrategia().persistir(doc, caza);
			
			Element posicion = doc.createElement("Posicion");
			caza.appendChild(posicion);
			this.getPosicion().persistir(doc, caza);
			
			Element direccion = doc.createElement("Direccion");
			caza.appendChild(direccion);
			this.getDireccion().persistir(doc, caza);
			
			Element velocidad = doc.createElement("Velocidad");
			caza.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			caza.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			caza.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			caza.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			caza.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element puntaje = doc.createElement("Puntaje");
			caza.appendChild(puntaje);
			puntaje.setTextContent(Integer.toString(this.getPuntaje()));
			
			Element activo = doc.createElement("Activo");
			caza.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			caza.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
		
			Element armas = doc.createElement("Armas");
			caza.appendChild(armas);
			for (int i = 0; i < (this.getArmas().size()); i ++) {
				this.getArmas().get(i).persistir(doc, armas);
			}		
	}
	
	public Movible recuperar(Element element, Caza caza) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Estrategia")) {
				if (child.getTextContent().equals("Circulo")) {
					Circulo circulo = new Circulo();
					caza.setEstrategia(circulo.recuperar(element, circulo));
				} else if (child.getTextContent().equals("Ida y Vuelta")) {
					IdaVuelta idaVuelta = new IdaVuelta();
					caza.setEstrategia(idaVuelta.recuperar(element, idaVuelta));
				} else if (child.getTextContent().equals("Linea Horizontal")) {
					LineaHorizontal lineaHorizontal = new LineaHorizontal();
					caza.setEstrategia(lineaHorizontal.recuperar(element, lineaHorizontal));
				} else if (child.getTextContent().equals("Zigzag")) {
					ZigZag zigZag = new ZigZag();
					caza.setEstrategia(zigZag.recuperar(element, zigZag));
				}
			} else if (child.getNodeName().equals("Velocidad")) {
				caza.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				caza.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				caza.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				caza.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				caza.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Puntaje")) {
				caza.setPuntaje(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					caza.setActivo(true);
				} else {
					caza.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					caza.setExpansible(true);
				} else {
					caza.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				caza.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					caza.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					caza.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					caza.setDireccion(new Derecha());
				} else {
					caza.setDireccion(new Izquierda());
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
		return caza;
	}
}