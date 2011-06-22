package algo42.modelo;

import java.util.Calendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CantidadDeBalasIncorrecta;

public class TorpedoAdaptable extends Arma {

	private Arma armaQueUsa;
	private int tiempoDesdeEleccionDeArma;
	
	public TorpedoAdaptable(int cantidadDeBalas, int equipo) throws CantidadDeBalasIncorrecta {
		super(cantidadDeBalas, equipo);
		int numeroAlAzar = (int) (Math.random()*100+1);
		if ((numeroAlAzar >= 0)&&(numeroAlAzar < 50)) { 			// 50% de probabilidad de usar laser
			this.armaQueUsa = new Laser(-1,2);
		} else {
			if ((numeroAlAzar >= 50)&&(numeroAlAzar < 70)) {		// 20% de probabilidad de usar cohete
				this.armaQueUsa = new Cohete(-1,2);
			} else {
				if ((numeroAlAzar >= 70)&&(numeroAlAzar < 90)) {	// 20% de probabilidad de usar torpedos simples
					this.armaQueUsa = new TorpedoSimple(-1,2);
				} else {
					this.armaQueUsa = new TorpedoRastreador(-1,2);	// 10% de probabilidad de usar torpedos rastreadores
				}
			}
		}
		Calendar calendario = Calendar.getInstance();
		this.tiempoDesdeEleccionDeArma = calendario.get(Calendar.SECOND);
	}

	public void cambiarArmaQueUsa(NaveArmada nave) {
		nave.removerTodasLasArmas();
		try {
			nave.agregarArma(new TorpedoAdaptable(-1, 2));
		} catch (CantidadDeBalasIncorrecta e) {
			// Nunca se llega a tirar esta excepcion
		}
	}
	
	public Bala getBala() {
		Calendar calendario = Calendar.getInstance();
		int tiempoActual = calendario.get(Calendar.SECOND);
		if (tiempoActual - this.tiempoDesdeEleccionDeArma >= 10) { // Cada 10 segundos cambia el arma con que dispara
			this.cambiarArmaQueUsa(this.getNave());
		}
		return this.armaQueUsa.getBala();
	}
	
	public void persistir(Document doc, Element armas) {
		Element torpedoAdaptable = doc.createElement("Torpedo Adaptable");
		armas.appendChild(torpedoAdaptable);
		
			Element cantidadDeBalas = doc.createElement("Cantidad De Balas");
			torpedoAdaptable.appendChild(cantidadDeBalas);
			cantidadDeBalas.setTextContent(Integer.toString(this.getCantidadDeBalas()));
		
			Element equipo = doc.createElement("Equipo");
			torpedoAdaptable.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.getEquipo()));
			
			Element armaQueUsa = doc.createElement("Arma Que Usa");
			torpedoAdaptable.appendChild(armaQueUsa);
			this.armaQueUsa.persistir(doc, armaQueUsa);
			
			Element tiempoDesdeEleccionDeArma = doc.createElement("Tiempo Desde Eleccion De Arma");
			torpedoAdaptable.appendChild(tiempoDesdeEleccionDeArma);
			tiempoDesdeEleccionDeArma.setTextContent(Integer.toString(this.tiempoDesdeEleccionDeArma));
	}
	
	public Arma recuperar(Element element, TorpedoAdaptable torpedoAdaptable) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Cantidad De Balas")) {
				torpedoAdaptable.setCantidadDeBalas(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Equipo")) {
				torpedoAdaptable.setEquipo(Integer.parseInt(child.getTextContent()));
			} else if (child.getNodeName().equals("Arma Que Usa")) {
				try {
					if (child.getTextContent() == "Cohete") {
						Cohete cohete = new Cohete(-2, -2);
						torpedoAdaptable.armaQueUsa = cohete.recuperar(element, cohete);
					} else if (child.getTextContent() == "Laser") {
						Laser laser = new Laser(-2, -2);
						torpedoAdaptable.armaQueUsa = laser.recuperar(element, laser);
					} else if (child.getTextContent() == "Torpedo Rastreador") {
						TorpedoRastreador torpedoRastreador = new TorpedoRastreador(-2, -2);
						torpedoAdaptable.armaQueUsa = torpedoRastreador.recuperar(element, torpedoRastreador);
					} else {
						Cohete cohete = new Cohete(-2, -2);
						torpedoAdaptable.armaQueUsa = cohete.recuperar(element, cohete);
					}
				} catch (CantidadDeBalasIncorrecta e) {
					// Nunca se llega a tirar esta excepcion
				}
			} else if (child.getNodeName().equals("Tiempo Desde Eleccion De Arma")) {
				torpedoAdaptable.tiempoDesdeEleccionDeArma = Integer.parseInt(child.getTextContent());
			}
		}
		return torpedoAdaptable;
	}
}
