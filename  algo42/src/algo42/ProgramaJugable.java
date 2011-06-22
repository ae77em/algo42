package algo42;

import java.util.ArrayList;
import java.util.Calendar;

import algo42.modelo.Flota;
import algo42.modelo.Juego;
import algo42.modelo.Movible;
import algo42.modelo.Nave;
import algo42.titiritero.ControladorJuego;
import algo42.titiritero.Dibujable;
import algo42.titiritero.KeyPressedObservador;
import algo42.vista.VistaMovibles;

public class ProgramaJugable {
	
	private Juego juego = new Juego();
	private ArrayList<Movible> navesEnemigas = new ArrayList<Movible>();
	private Nave naveActual;
	private Calendar calendario = Calendar.getInstance();
	private int tiempoActual = calendario.get(Calendar.SECOND);
	private ControladorJuego controlador;
	
	public ProgramaJugable(ControladorJuego unControlador){
		this.controlador = unControlador;
	}
	
	public void comenzarJuego(){
		
    	while (juego.getMision().getPuntajeDelJugador() < 1000) {
    	    if (tiempoActual - juego.getMision().getTiempoDesdeLaUltimaFlotaCreada() >= 5) { 					// Cada 5 segundos crea flotas y naves civiles
    	    	juego.getMision().setTiempoDesdeLaUltimaFlotaCreada(calendario.get(Calendar.SECOND));
    	       	juego.getMision().setFlota(new Flota());	
    	       	juego.getMision().crearNaveNoEnemiga();	
    	    	navesEnemigas = juego.getMision().getFlota().getNaves();
    	    	for (int i = 0; i < 5; i++) {
    	    		naveActual = (Nave) navesEnemigas.get(i);
    	    		juego.getMision().getEspacioAereo().add(naveActual);
    	    		naveActual.activar(juego.getMision(), juego.getMision().getFlota().getPosicionNave(i));
    	    		naveActual.actuar();
    	    	}
    	    }

			ArrayList<Movible> espacioAereo = juego.getMision().getEspacioAereo();
			ArrayList<Dibujable> vistaMovibles = new ArrayList<Dibujable>();
			for (int i = 0; i < espacioAereo.size(); i++) {
				if (espacioAereo.get(i) != juego.getMision().getJugador()) {
					String tipo = espacioAereo.get(i).getClass().toString();
					if (tipo == "Algo42") {
			    		VistaMovibles vistaAlgo42 = new VistaMovibles();
			    		vistaAlgo42.setPosicionable(espacioAereo.get(i));
			    		vistaAlgo42.setNombreArchivoImagen("pacman.jpg");
			    		controlador.agregarDibujable(vistaAlgo42);
			    		vistaMovibles.add(vistaAlgo42);
			    		controlador.agregarObjetoVivo(espacioAereo.get(i));
			    		controlador.agregarKeyPressObservador((KeyPressedObservador) espacioAereo.get(i));
					} else if (tipo == "Avioneta") {
							VistaMovibles vistaAvioneta = new VistaMovibles();
							vistaAvioneta.setNombreArchivoImagen("fantasma1.jpg");
							vistaAvioneta.setPosicionable(espacioAereo.get(i));
							controlador.agregarDibujable(vistaAvioneta);
							vistaMovibles.add(vistaAvioneta);
							controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "Bombardero") {
						VistaMovibles vistaBombardero = new VistaMovibles();
						vistaBombardero.setNombreArchivoImagen("fantasma1.jpg");
						vistaBombardero.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaBombardero);
						vistaMovibles.add(vistaBombardero);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "Caza") {
						VistaMovibles vistaCaza = new VistaMovibles();
						vistaCaza.setNombreArchivoImagen("fantasma1.jpg");
						vistaCaza.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaCaza);
						vistaMovibles.add(vistaCaza);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "Explorador") {
						VistaMovibles vistaExplorador = new VistaMovibles();
						vistaExplorador.setNombreArchivoImagen("fantasma1.jpg");
						vistaExplorador.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaExplorador);
						vistaMovibles.add(vistaExplorador);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "Guia") {
						VistaMovibles vistaGuia = new VistaMovibles();
						vistaGuia.setNombreArchivoImagen("fantasma1.jpg");
						vistaGuia.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaGuia);
						vistaMovibles.add(vistaGuia);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "Helicoptero") {
						VistaMovibles vistaHelicoptero = new VistaMovibles();
						vistaHelicoptero.setNombreArchivoImagen("fantasma1.jpg");
						vistaHelicoptero.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaHelicoptero);
						vistaMovibles.add(vistaHelicoptero);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "Civil") {
						VistaMovibles vistaCivil = new VistaMovibles();
						vistaCivil.setNombreArchivoImagen("fantasma1.jpg");
						vistaCivil.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaCivil);
						vistaMovibles.add(vistaCivil);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "BalaCohete") {
						VistaMovibles vistaBalaCohete = new VistaMovibles();
						vistaBalaCohete.setNombreArchivoImagen("fantasma1.jpg");
						vistaBalaCohete.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaBalaCohete);
						vistaMovibles.add(vistaBalaCohete);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "BalaLaser") {
						VistaMovibles vistaBalaLaser = new VistaMovibles();
						vistaBalaLaser.setNombreArchivoImagen("fantasma1.jpg");
						vistaBalaLaser.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaBalaLaser);
						vistaMovibles.add(vistaBalaLaser);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "BalaTorpedoRastreador") {
						VistaMovibles vistaBalaTorpedoRastreador = new VistaMovibles();
						vistaBalaTorpedoRastreador.setNombreArchivoImagen("fantasma1.jpg");
						vistaBalaTorpedoRastreador.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaBalaTorpedoRastreador);
						vistaMovibles.add(vistaBalaTorpedoRastreador);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					} else if (tipo == "BalaTorpedoSimple") {
						VistaMovibles vistaBalaTorpedoSimple = new VistaMovibles();
						vistaBalaTorpedoSimple.setNombreArchivoImagen("fantasma1.jpg");
						vistaBalaTorpedoSimple.setPosicionable(espacioAereo.get(i));
						controlador.agregarDibujable(vistaBalaTorpedoSimple);
						vistaMovibles.add(vistaBalaTorpedoSimple);
						controlador.agregarObjetoVivo(espacioAereo.get(i));
					}
				}
			}
			
			
			controlador.setIntervaloSimulacion(7);
			
			
			while (juego.getMision().getActivo() == true) {
				controlador.comenzar();
				for (int i = 0; i < juego.getMision().getEspacioAereo().size(); i++) {
					if (juego.getMision().getEspacioAereo().get(i).getActivo() == false) {
						controlador.removerDibujable(vistaMovibles.get(i));
					}
				}
			}
    	}
    	juego.getMision().misionCompleta();
	}
}
