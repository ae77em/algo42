package algo42.modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class CajaArmas extends Objeto {

	private ArrayList<Arma> armas = new ArrayList<Arma>();
	
	public CajaArmas() {
		super();
		Cohete cohete = null;
		TorpedoRastreador torpedoRastreador = null;
		
		try {
			cohete = new Cohete(30, 1);
			torpedoRastreador = new TorpedoRastreador(20, 1);		
		} catch (CantidadDeBalasIncorrecta e) {
			e.printStackTrace();
		}
		this.armas.add(cohete);
		this.armas.add(torpedoRastreador);
	}
	
	public void consumirPor(Algo42 algo42) {
		if (this.getActivo() == true) {
			for (int i = 0; i <= armas.size(); i++) {
				algo42.cargar(armas.remove(0));
			}
			this.destruir();
		}
	}
	
	public void persistir(Document doc, Element espacioAereo) {
		Element cajaArmas = doc.createElement("Caja Armas");
		espacioAereo.appendChild(cajaArmas);
			
			Element posicion = doc.createElement("Posicion");
			cajaArmas.appendChild(posicion);
			this.getPosicion().persistir(doc, cajaArmas);
			
			Element direccion = doc.createElement("Direccion");
			cajaArmas.appendChild(direccion);
			this.getDireccion().persistir(doc, cajaArmas);
			
			Element velocidad = doc.createElement("Velocidad");
			cajaArmas.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.getVelocidad()));
			
			Element energia = doc.createElement("Energia");
			cajaArmas.appendChild(energia);
			energia.setTextContent(Integer.toString(this.getEnergia()));
			
			Element equipo = doc.createElement("Equipo");
			cajaArmas.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element danio = doc.createElement("Daño");
			cajaArmas.appendChild(danio);
			danio.setTextContent(Integer.toString(this.getDanio()));
			
			Element tamanio = doc.createElement("Tamaño");
			cajaArmas.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.getTamanio()));
			
			Element activo = doc.createElement("Activo");
			cajaArmas.appendChild(activo);
			if (this.getActivo() == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			cajaArmas.appendChild(expansible);
			if (this.getExpansible() == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
		
			Element armas = doc.createElement("Armas");
			cajaArmas.appendChild(armas);
			for (int i = 0; i < (this.armas.size()); i ++) {
				this.armas.get(i).persistir(doc, armas);
			}		
	}
	
	public Movible recuperar(Element element, CajaArmas cajaArmas) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Velocidad")) {
				cajaArmas.setVelocidad(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Energia")) {
				cajaArmas.setEnergia(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				cajaArmas.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Daño")) {
				cajaArmas.setDanio(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Tamaño")) {
				cajaArmas.setTamanio(Integer.parseInt(child.getTextContent()));			
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					cajaArmas.setActivo(true);
				} else {
					cajaArmas.setActivo(false);
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					cajaArmas.setExpansible(true);
				} else {
					cajaArmas.setExpansible(false);
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				cajaArmas.setPosicion(posicion.recuperar(element, posicion));
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					cajaArmas.setDireccion(new Abajo());
				} else if (child.getTextContent() == "Arriba") {
					cajaArmas.setDireccion(new Arriba());
				} else if (child.getTextContent() == "Derecha") {
					cajaArmas.setDireccion(new Derecha());
				} else {
					cajaArmas.setDireccion(new Izquierda());
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
		return cajaArmas;
	}
}