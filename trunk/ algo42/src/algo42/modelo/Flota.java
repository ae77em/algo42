package algo42.modelo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Flota {	

	private ArrayList<Punto> posicionesNaves = new ArrayList<Punto>();
	private ArrayList<Movible> naves = new ArrayList<Movible>();
	
	public void inicializarFlota(int numeroDeMision) {
		Nave naveActual;
		Guia naveGuia;
		int numeroAlAzar = (int) (Math.random()*93+1);
		int otroNumeroAlAzar = (int) (Math.random()*4+1);
		int otroOtroNumeroAlAzar;
		while ((numeroAlAzar >= 1)&&(numeroAlAzar <= 7)) {
			numeroAlAzar = (int) (Math.random()*93+1);
		}
		this.posicionesNaves.add(new Punto(numeroAlAzar, 12));
		naveGuia = new Guia();
		this.naves.add(naveGuia);
		
		for (int i = 1; i <= 5; i++) {
			if (i == 1) {
				this.posicionesNaves.add(new Punto(numeroAlAzar - 6, 2));
			} else {
				if (i == 2) {
					this.posicionesNaves.add(new Punto(numeroAlAzar - 3, 5));
				} else {
					if (i == 3) {
						this.posicionesNaves.add(new Punto(numeroAlAzar, 8));
					} else {
						if (i == 4) {
							this.posicionesNaves.add(new Punto(numeroAlAzar + 3, 5));
						} else {
							this.posicionesNaves.add(new Punto(numeroAlAzar + 6, 2));
						}
					}
				}
			}
			if (otroNumeroAlAzar == 1) {
				naveActual = new Caza();
			} else {
				otroOtroNumeroAlAzar = (int) (Math.random()*3+1);
				if (otroOtroNumeroAlAzar == 1) {
					naveActual = new Avioneta();
				} else {
					if (otroOtroNumeroAlAzar == 2) {
						naveActual = new Explorador();
					} else {
						naveActual = new Bombardero();
					}
				}
			}
			this.naves.add(naveActual);
			naveGuia.setNaveDeFlota(naveActual);
		}
	}
	
	public ArrayList<Movible> getNaves() {
		return this.naves;
	}

	public Punto getPosicionNave(int i) {
		return this.posicionesNaves.remove(i);
	}

	public void persistir(Document doc, Element mision) {
		Element posicionesNaves = doc.createElement("Posiciones De Naves");
		mision.appendChild(posicionesNaves);
		for (int i = 0; i < (this.posicionesNaves.size()); i ++) {
			this.posicionesNaves.get(i).persistir(doc, posicionesNaves);
		}
		
		Element naves = doc.createElement("Naves");
		mision.appendChild(naves);
		for (int i = 0; i < (this.naves.size()); i ++) {
			this.naves.get(i).persistir(doc, naves);
		}
	}

	public Flota recuperar(Element element, Flota flota) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Posiciones De Naves")) {
				ArrayList<Punto> posicionesNaves = new ArrayList<Punto>();
				NodeList childs2 = element.getChildNodes();
				for (int j = 0; i < childs2.getLength(); i++) {
					Node child2 = childs2.item(j);
					Punto posicion = new Punto(0,0);
					posicionesNaves.add(posicion.recuperar(element, posicion));
				}
				flota.posicionesNaves = posicionesNaves;
			} else if (child.getNodeName().equals("Naves")) {
				ArrayList<Movible> naves = new ArrayList<Movible>();
				NodeList childs2 = element.getChildNodes();
				for (int j = 0; i < childs2.getLength(); i++) {
					Node child2 = childs2.item(j);
					if (child2.getNodeName().equals("Avioneta")) {
						Avioneta avioneta = new Avioneta();
						naves.add(avioneta.recuperar(element, avioneta));
					} else if (child2.getNodeName().equals("Bombardero")) {
						Bombardero bombardero = new Bombardero();
						naves.add(bombardero.recuperar(element, bombardero));
					} else if (child2.getNodeName().equals("Caza")) {
						Caza caza = new Caza();
						naves.add(caza.recuperar(element, caza));
					} else if (child2.getNodeName().equals("Explorador")) {
						Explorador explorador = new Explorador();
						naves.add(explorador.recuperar(element, explorador));
					} else if (child2.getNodeName().equals("Guia")) {
						Guia guia = new Guia();
						naves.add(guia.recuperar(element, guia));
					} else if (child2.getNodeName().equals("Helicoptero")) {
						Helicoptero helicoptero = new Helicoptero();
						naves.add(helicoptero.recuperar(element, helicoptero));
					} 
				}
				flota.naves = naves;
			}
		}
		return flota;
	}
}