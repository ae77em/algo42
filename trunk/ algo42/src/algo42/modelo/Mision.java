package algo42.modelo;

import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import algo42.modelo.excepciones.CoordenadaFueraDeRangoError;

public class Mision {
        
	private boolean activo;
    private ArrayList<Movible> espacioAereo;
    private Flota flota;
    private Juego juego;
    private Algo42 jugador;
    private int puntajeJugador, tiempoDesdeLaUltimaFlotaCreada;
    private Punto puntoInicialJugador;
    
    public Mision (Juego juego) {
    	this.juego = juego;
    	this.puntoInicialJugador = new Punto(51*5,99*5);
    	this.puntajeJugador = 0;
    	this.espacioAereo = new ArrayList<Movible>();
    	this.jugador = new Algo42();
    	this.ubicarNaveDelJugador();
    	this.tiempoDesdeLaUltimaFlotaCreada = 0;
    }
    
    public void comenzar() {
    	this.activo = true;
    }
    
    public void crearNaveNoEnemiga() {
    	int numeroAlAzar = (int) (Math.random()*2+1);
    	int otroNumeroAlAzar;
    	Nave naveNoEnemiga;
    	if (numeroAlAzar == 1) {
    		naveNoEnemiga = new Civil();
    		otroNumeroAlAzar = (int) (Math.random()*499+1);
    		while (otroNumeroAlAzar == 1) {
    			otroNumeroAlAzar = (int) (Math.random()*499+1);
    		}
    		naveNoEnemiga.activar(this, new Punto(2*5, otroNumeroAlAzar*5));
    	} else {
    		naveNoEnemiga = new Helicoptero();
    		naveNoEnemiga.activar(this, new Punto(51*5, 2*5));
    	}
    	this.espacioAereo.add(naveNoEnemiga);
    	naveNoEnemiga.actuar();
    }
    
    public void aumentarPuntaje (int puntaje) {
    	this.puntajeJugador = this.puntajeJugador + puntaje;
    }
    
    public Movible hayAlguien(Movible objetoAMover, Punto posicion) throws CoordenadaFueraDeRangoError {
    	Movible casillero = null;
    	
    	if((posicion.getX() <= 1*5) || (posicion.getX() >= 100*5) || (posicion.getY() <= 1*5) || (posicion.getY() >= 100*5)) {
    		throw new CoordenadaFueraDeRangoError();
    	} else {                  
    		for (int i = 0; i < (this.espacioAereo.size()); i ++) {
    			if (this.espacioAereo.get(i).getPosicion().distancia(posicion) <= (this.espacioAereo.get(i).getTamanio() + objetoAMover.getTamanio())){
    				if (this.espacioAereo.get(i) != objetoAMover){
    					casillero = this.espacioAereo.get(i);
    				}
    			}
    		}
    	}
    	return casillero;
    }		
    
    public void ubicarBalaEnPosicion(Bala bala, Punto posicion) {
    	ResolvedorDeChoque resolvedorDeChoque;
    	TipoDeChoque tipoDeChoque;
    	Movible objeto = null;
    	
    	try {
    		objeto = this.hayAlguien(bala, posicion);
    	} catch (CoordenadaFueraDeRangoError e) {
    		// Nunca se llega a tirar esta excepcion
    	}
    	
    	if (objeto == null) {
    		this.espacioAereo.add(bala);
    	} else {
    		this.espacioAereo.add(bala);
    		resolvedorDeChoque = new ResolvedorDeChoque();
    		tipoDeChoque = resolvedorDeChoque.resolver(bala, objeto);
    		tipoDeChoque.chocarEntre(bala, objeto);
    	}
    }
    
    public void ubicarNaveDelJugador() {
    	this.espacioAereo.add(this.jugador);
    	this.jugador.activar(this, this.puntoInicialJugador);
    }
    
    public void ubicarObjetoEnPosicion(Movible objeto, Punto posicion) {
    	objeto.setPosicion(posicion);
    	this.espacioAereo.add(objeto);
    	objeto.activar(this, posicion);
    }
    
    public Movible getObjetoEnPosicion(Punto posicion) {
    	for (int i = 0; i < (this.espacioAereo.size()); i++) {
    		if (this.espacioAereo.get(i).getPosicion().equals(posicion)){
    			return this.espacioAereo.get(i);
    		}
    	}
    	return null;
    }

	public void misionCompleta() {
    	this.activo = false;
    	this.juego.ganaste();
    }
    
    public void misionPerdida() {
    	this.activo = false;
    	this.juego.perdiste();
    }
    
    public boolean getActivo() {
    	return this.activo;
    }
    
    public Algo42 getJugador() {
    	return this.jugador;
    }
    
    public int getPuntajeDelJugador() {
    	return this.puntajeJugador;
    }
    
    public Punto getPosicionDelJugador() {
    	return this.jugador.getPosicion();
    }

	public ArrayList<Movible> getEspacioAereo() {
		return this.espacioAereo;
	}
	
	public int getTiempoDesdeLaUltimaFlotaCreada() {
		return this.tiempoDesdeLaUltimaFlotaCreada;
	}
	
	public void setTiempoDesdeLaUltimaFlotaCreada(int tiempoDesdeLaUltimaFlotaCreada) {
		this.tiempoDesdeLaUltimaFlotaCreada = tiempoDesdeLaUltimaFlotaCreada;
	}
	
	public Flota getFlota() {
		return this.flota;
	}
	
	public void setFlota(Flota flota) {
		this.flota = flota;
	}

	public void persistir(Document doc, Element mision) {
		Element activo = doc.createElement("Activo");
		mision.appendChild(activo);
		if (this.activo == true) {
			activo.setTextContent("True");
		} else {
			activo.setTextContent("False");
		}
		
		Element espacioAereo = doc.createElement("Espacio Aereo");
		mision.appendChild(espacioAereo);
		for (int i = 0; i < (this.espacioAereo.size()); i ++) {
			this.espacioAereo.get(i).persistir(doc, espacioAereo);
		}
		
		Element flota = doc.createElement("Flota");
		mision.appendChild(flota);
		this.flota.persistir(doc, flota);
		
		Element puntajeJugador = doc.createElement("Puntaje Del Jugador");
		mision.appendChild(puntajeJugador);
		puntajeJugador.setTextContent(Integer.toString(this.puntajeJugador));
		
		Element puntoInicialJugador = doc.createElement("Punto Inicial Del Jugador");
		mision.appendChild(puntoInicialJugador);
		this.puntoInicialJugador.persistir(doc, puntoInicialJugador);
		
		Element tiempoDesdeLaUltimaFlotaCreada = doc.createElement("Tiempo Desde La Ultima Flota Creada");
		mision.appendChild(tiempoDesdeLaUltimaFlotaCreada);
		tiempoDesdeLaUltimaFlotaCreada.setTextContent(Integer.toString(this.tiempoDesdeLaUltimaFlotaCreada));
	}

	public Mision recuperar(Element element, Mision mision) {
		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);
			if (child.getNodeName().equals("Activo")) {
				if (child.getTextContent() == "True") {
					mision.activo = true;
				} else {
					mision.activo = false;
				}
			} else if (child.getNodeName().equals("Espacio Aereo")) {
				ArrayList<Movible> espacioAereo = new ArrayList<Movible>();
				NodeList childs2 = element.getChildNodes();
				for (int j = 0; i < childs2.getLength(); i++) {
					Node child2 = childs2.item(j);
					if (child2.getNodeName().equals("Algo42")) {
						Algo42 algo42 = new Algo42();
						espacioAereo.add(algo42.recuperar(element, algo42));
					} else if (child2.getNodeName().equals("Avioneta")) {
						Avioneta avioneta = new Avioneta();
						espacioAereo.add(avioneta.recuperar(element, avioneta));
					} else if (child2.getNodeName().equals("Bala Cohete")) {
						BalaCohete balaCohete = new BalaCohete(-1);
						espacioAereo.add(balaCohete.recuperar(element, balaCohete));
					} else if (child2.getNodeName().equals("Bala Laser")) {
						BalaLaser balaLaser = new BalaLaser(-1);
						espacioAereo.add(balaLaser.recuperar(element, balaLaser));
					} else if (child2.getNodeName().equals("Bala Torpedo Rastreador")) {
						BalaTorpedoRastreador balaTorpedoRastreador = new BalaTorpedoRastreador(-1);
						espacioAereo.add(balaTorpedoRastreador.recuperar(element, balaTorpedoRastreador));
					} else if (child2.getNodeName().equals("Bala Torpedo Simple")) {
						BalaTorpedoSimple balaTorpedoSimple = new BalaTorpedoSimple(-1);
						espacioAereo.add(balaTorpedoSimple.recuperar(element, balaTorpedoSimple));
					} else if (child2.getNodeName().equals("Bombardero")) {
						Bombardero bombardero = new Bombardero();
						espacioAereo.add(bombardero.recuperar(element, bombardero));
					} else if (child2.getNodeName().equals("Caja Armas")) {
						CajaArmas cajaArmas = new CajaArmas();
						espacioAereo.add(cajaArmas.recuperar(element, cajaArmas));
					} else if (child2.getNodeName().equals("Caja Energia")) {
						CajaEnergia cajaEnergia = new CajaEnergia();
						espacioAereo.add(cajaEnergia.recuperar(element, cajaEnergia));
					} else if (child2.getNodeName().equals("Caza")) {
						Caza caza = new Caza();
						espacioAereo.add(caza.recuperar(element, caza));
					} else if (child2.getNodeName().equals("Civil")) {
						Civil civil = new Civil();
						espacioAereo.add(civil.recuperar(element, civil));
					} else if (child2.getNodeName().equals("Explorador")) {
						Explorador explorador = new Explorador();
						espacioAereo.add(explorador.recuperar(element, explorador));
					} else if (child2.getNodeName().equals("Guia")) {
						Guia guia = new Guia();
						espacioAereo.add(guia.recuperar(element, guia));
					} else if (child2.getNodeName().equals("Helicoptero")) {
						Helicoptero helicoptero = new Helicoptero();
						espacioAereo.add(helicoptero.recuperar(element, helicoptero));
					} 
				}
				mision.espacioAereo = espacioAereo;
			} else if (child.getNodeName().equals("Flota")) {
				Flota flota = new Flota();
				mision.flota = flota.recuperar(element, flota);
			} else if (child.getNodeName().equals("Puntaje Del Jugador")) {
				mision.puntajeJugador = Integer.parseInt(child.getTextContent());
			} else if (child.getNodeName().equals("Punto Inicial Del Jugador")) {
				Punto puntoInicialJugador = new Punto(0,0);
				mision.puntoInicialJugador = puntoInicialJugador.recuperar(element, puntoInicialJugador);
			} else if (child.getNodeName().equals("Tiempo Desde La Ultima Flota Creada")) {
				mision.tiempoDesdeLaUltimaFlotaCreada = Integer.parseInt(child.getTextContent());
			}
		}
		
		return mision;
	}
}	