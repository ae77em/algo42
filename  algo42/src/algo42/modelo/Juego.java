package algo42.modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Juego {
	
	private Mision mision;
	private int ganaste;
	
	public Juego() {
		this.mision = new Mision(this);
		this.ganaste = 0;
	}
	
//	public void comenzar() {
//		this.mision.comenzar();	
//	}
	
	public void cargarJuego() {
		Persistencia persistencia = new Persistencia();
		String archivo = "d:\\algo42.xml";
		persistencia.recuperarJuego(archivo);
	}
	
	public void guardarJuego() {
		Persistencia persistencia = new Persistencia();
		String archivo = "d:\\algo42.xml";
		persistencia.persistirJuego(archivo, this);
	}
	
	public Element getElement(Document doc) {
		Element juego = doc.createElement("Juego");
		
			Element mision = doc.createElement("Mision");
			juego.appendChild(mision);
			this.mision.persistir(doc, mision);
		
			Element ganaste = doc.createElement("Ganaste");
			juego.appendChild(ganaste);
			ganaste.setTextContent(Integer.toString(this.ganaste));

		return juego;
	}
	
	public static Juego fromElement(Element element) {
		Juego juego = new Juego();

		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Mision")) {
				Mision mision = new Mision(juego);
				juego.mision = mision.recuperar(element, mision);
			} else if (child.getNodeName().equals("Ganaste")) {
				juego.ganaste = Integer.parseInt(child.getTextContent());
			}
		}
		return juego;
	}

	public void ganaste() {
		this.ganaste = 1;	/*GANASTE*/
	}
	
	public void perdiste() {
		this.ganaste = -1;	/*PERDISTE*/
	}
	
	public int getGanaste() {
		return this.ganaste;
	}
	
	public Mision getMision() {
		return this.mision;
	}
}