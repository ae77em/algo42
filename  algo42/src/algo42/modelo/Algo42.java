package algo42.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.titiritero.*;
import algo42.modelo.excepciones.*;

public class Algo42 implements Movible, KeyPressedObservador {
	
	private ArrayList<Arma> armas = new ArrayList<Arma>();
	private int velocidad, energia, equipo, danio, tamanio;
	private boolean activo, expansible;
    private Punto posicion;
    private Direccion direccion;
    private Mision tablero;
        
    public Algo42() {
    	Laser laser = null;
        try {
        	laser = new Laser(-1, 1);
        } catch (CantidadDeBalasIncorrecta e) {
        	// Nunca se llega a tirar esta excepcion
        }
        this.armas.add(laser);
        this.velocidad = 2;
        this.energia = 1000;
        this.activo = false;
        this.equipo = 1;
        this.expansible = true;
        this.danio = 1000;
        this.direccion = null;
        this.tamanio = 3;
        this.posicion = new Punto(0, 0);
    }
        
    public void activar(Mision tablero, Punto puntoInicialJugador) {
    	this.activo = true;
        this.tablero = tablero;
        this.posicion = puntoInicialJugador;
        this.direccion = new Arriba();
    }
        
    public void volar(Direccion direccion) {
    	this.direccion = direccion;
        this.direccion.trasladar(this, this.tablero);
    }
        
    public void disparar() {
    	Iterator<Arma> iterador = armas.iterator();
    	
    	while (iterador.hasNext()) {
    		iterador.next().disparar(this.posicion, this.tablero, this.direccion);
    	} 
    }
        
    public void cargar(Arma arma) {
    	this.armas.add(arma);
    }
        
    public void aumentarEnergia(int cantidadEnergia) throws CantidadDeEnergiaIncorrecta {
    	if (this.activo == true) {
        	if (cantidadEnergia <= 0) {
        		throw new CantidadDeEnergiaIncorrecta();
            } else {
            	if ((1000 - this.energia) >= cantidadEnergia) {
                        this.energia = this.energia + cantidadEnergia;
                } else {
                	this.energia = 1000;
                }
            }
    	}
    }
    
    public void disminuirEnergia (int danio) {
    	if ((this.energia - danio) <= 0){
    		this.destruir();
        } else {
        	this.energia = this.energia - danio;
        }
    }
        
    public void destruir(){
    	this.energia = 0;
        this.activo = false;
        this.tablero.misionPerdida();
    }
        
    public void setDireccion(Direccion direccion) {
    	this.direccion = direccion;
    }
    
    public Punto getPosicion() {
    	return this.posicion;
    }
        
    public void setPosicion(Punto posicion) {
    	this.posicion = posicion;
    }

    public boolean getActivo() {
    	return this.activo;
    }
        
    public ArrayList<Arma> getArmas() {
    	return this.armas;
    }
        
    public int getDanio() {
    	return this.danio;
    }
        
    public int getEnergia() {
    	return this.energia;
    }
        
    public int getEquipo() {
    	return this.equipo;
    }
        
    public boolean getExpansible() {
    	return this.expansible;
    }
        
    public int getVelocidad() {
    	return this.velocidad;
    }
        
    public int getTamanio() {
    	return this.tamanio;
    }

	public void mover() {
		this.volar(this.direccion);
	}

	public void persistir(Document doc, Element espacioAereo) {
		Element algo42 = doc.createElement("Algo42");
		espacioAereo.appendChild(algo42);
		
			Element armas = doc.createElement("Armas");
			algo42.appendChild(armas);
			for (int i = 0; i < (this.armas.size()); i ++) {
				this.armas.get(i).persistir(doc, armas);
			}
			
			Element velocidad = doc.createElement("Velocidad");
			algo42.appendChild(velocidad);
			velocidad.setTextContent(Integer.toString(this.velocidad));
			
			Element energia = doc.createElement("Energia");
			algo42.appendChild(energia);
			energia.setTextContent(Integer.toString(this.energia));
			
			Element equipo = doc.createElement("Equipo");
			algo42.appendChild(equipo);
			equipo.setTextContent(Integer.toString(this.equipo));
			
			Element danio = doc.createElement("Daño");
			algo42.appendChild(danio);
			danio.setTextContent(Integer.toString(this.danio));
			
			Element tamanio = doc.createElement("Tamaño");
			algo42.appendChild(tamanio);
			tamanio.setTextContent(Integer.toString(this.tamanio));
			
			Element activo = doc.createElement("Activo");
			algo42.appendChild(activo);
			if (this.activo == true) {
				activo.setTextContent("True");
			} else {
				activo.setTextContent("False");
			}
			
			Element expansible = doc.createElement("Expansible");
			algo42.appendChild(expansible);
			if (this.expansible == true) {
				expansible.setTextContent("True");
			} else {
				expansible.setTextContent("False");
			}
			
			Element posicion = doc.createElement("Posicion");
			algo42.appendChild(posicion);
			this.posicion.persistir(doc, algo42);
			
			Element direccion = doc.createElement("Direccion");
			algo42.appendChild(direccion);
			this.direccion.persistir(doc, algo42);
	}

	public Movible recuperar(Element element, Algo42 algo42) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Armas")) {
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
			} else if (child.getNodeName().equals("Velocidad")) {
				algo42.velocidad = Integer.parseInt(child.getTextContent());
			} else if (child.getNodeName().equals("Energia")) {
				algo42.energia = Integer.parseInt(child.getTextContent());
			} else if (child.getNodeName().equals("Equipo")) {
				algo42.equipo = Integer.parseInt(child.getTextContent());
			} else if (child.getNodeName().equals("Daño")) {
				algo42.danio = Integer.parseInt(child.getTextContent());
			} else if (child.getNodeName().equals("Tamaño")) {
				algo42.tamanio = Integer.parseInt(child.getTextContent());
			} else if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					algo42.activo = true;
				} else {
					algo42.activo = false;
				}
			} else if (child.getNodeName().equals("Expansible")) {
				if (child.getTextContent() == "True") {
					algo42.expansible = true;
				} else {
					algo42.expansible = false;
				}
			} else if (child.getNodeName().equals("Posicion")) {
				Punto posicion = new Punto(0,0);
				algo42.posicion = posicion.recuperar(element, posicion);
			} else if (child.getNodeName().equals("Direccion")) {
				if (child.getTextContent() == "Abajo") {
					algo42.direccion = new Abajo();
				} else if (child.getTextContent() == "Arriba") {
					algo42.direccion = new Arriba();
				} else if (child.getTextContent() == "Derecha") {
					algo42.direccion = new Derecha();
				} else {
					algo42.direccion = new Izquierda();
				}
			}
		}
		return algo42;
	}

	public void keyPressed(KeyEvent event) {
		int aux = event.getKeyCode();
		switch (aux) {
			case (38):
				this.setDireccion(new Arriba());
				break;
			case (37):
				this.setDireccion(new Izquierda());
				break;
			case (39):
				this.setDireccion(new Derecha());
				break;
			case (40):
				this.setDireccion(new Abajo());
				break;
		}
	}

	public int getX() {
		return this.posicion.getX();
	}

	public int getY() {
		return this.posicion.getY();
	}

	public void vivir() {
		this.mover();
	}
}