package algo42;

import java.util.ArrayList;

import algo42.vista.VentanaIngresoNombre;
import algo42.vista.VentanaJuego;
import algo42.modelo.Cronometro;
import algo42.modelo.Flota;
import algo42.modelo.Juego;
import algo42.modelo.Movible;
import algo42.modelo.Nave;
import algo42.titiritero.ControladorJuego;
import algo42.titiritero.Dibujable;
import algo42.titiritero.KeyPressedObservador;
import algo42.vista.VistaMovibles;

public class ProgramaJugable {
	private static Juego juego = new Juego();
	private static ArrayList<Movible> navesEnemigas = new ArrayList<Movible>();
	private static Nave naveActual;
	private static Cronometro cronometro = new Cronometro();
	private static int tiempoActual = 0;
	private static ControladorJuego controlador = new ControladorJuego();
	
//	public ProgramaJugable(ControladorJuego unControlador){
//		controlador = unControlador;
//	}
	
	 public static void main(String[] args) {
		ArrayList<Dibujable> vistaMovibles = new ArrayList<Dibujable>();
		ArrayList<Movible> espacioAereo = juego.getMision().getEspacioAereo();
		cronometro.comenzar();
		
		VentanaJuego ventana = new VentanaJuego(controlador, 650, 550); 
		controlador.setSuperficieDeDibujo(ventana.getSuperficieDeDibujo());
		ventana.setVisible(true);
		
    	while (juego.getMision().getPuntajeDelJugador() < 1000) {
    	    if (tiempoActual - juego.getMision().getTiempoDesdeLaUltimaFlotaCreada() >= 5) { 					// Cada 5 segundos crea flotas y naves civiles
    	    	juego.getMision().setTiempoDesdeLaUltimaFlotaCreada(tiempoActual);
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
    	    tiempoActual = cronometro.getTiempoEnSegundos();
    	    
    	    Movible algo42 = juego.getMision().getJugador();
			VistaMovibles vistaAlgo42 = null;
			vistaAlgo42 = new VistaMovibles();
			vistaAlgo42.setPosicionable(algo42);
			vistaAlgo42.setNombreArchivoImagen("pacman.JPG");
			controlador.agregarDibujable(vistaAlgo42);
			controlador.agregarObjetoVivo(algo42);
			controlador.agregarKeyPressObservador((KeyPressedObservador) algo42);

//			for (int i = 0; i < espacioAereo.size(); i++) {
//				String tipo = espacioAereo.get(i).getClass().toString();
//				if (tipo == "Algo42") {
//			    	VistaMovibles vistaAlgo42 = new VistaMovibles();
//			    	vistaAlgo42.setPosicionable(espacioAereo.get(i));
//			    	vistaAlgo42.setNombreArchivoImagen("fondo.JPG");
//			    	controlador.agregarDibujable(vistaAlgo42);
//			    	vistaMovibles.add(vistaAlgo42);
//			    	controlador.agregarObjetoVivo(espacioAereo.get(i));
//			    	controlador.agregarKeyPressObservador((KeyPressedObservador) espacioAereo.get(i));
//				} else if (tipo == "Avioneta") {
//					VistaMovibles vistaAvioneta = new VistaMovibles();
//					vistaAvioneta.setNombreArchivoImagen("pacman.jpg");
//					vistaAvioneta.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaAvioneta);
//					vistaMovibles.add(vistaAvioneta);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "Bombardero") {
//					VistaMovibles vistaBombardero = new VistaMovibles();
//					vistaBombardero.setNombreArchivoImagen("pacman.jpg");
//					vistaBombardero.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaBombardero);
//					vistaMovibles.add(vistaBombardero);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "Caza") {
//					VistaMovibles vistaCaza = new VistaMovibles();
//					vistaCaza.setNombreArchivoImagen("pacman.jpg");
//					vistaCaza.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaCaza);
//					vistaMovibles.add(vistaCaza);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "Explorador") {
//					VistaMovibles vistaExplorador = new VistaMovibles();
//					vistaExplorador.setNombreArchivoImagen("pacman.jpg");
//					vistaExplorador.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaExplorador);
//					vistaMovibles.add(vistaExplorador);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "Guia") {
//					VistaMovibles vistaGuia = new VistaMovibles();
//					vistaGuia.setNombreArchivoImagen("pacman.jpg");
//					vistaGuia.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaGuia);
//					vistaMovibles.add(vistaGuia);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "Helicoptero") {
//					VistaMovibles vistaHelicoptero = new VistaMovibles();
//					vistaHelicoptero.setNombreArchivoImagen("pacman.jpg");
//					vistaHelicoptero.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaHelicoptero);
//					vistaMovibles.add(vistaHelicoptero);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "Civil") {
//					VistaMovibles vistaCivil = new VistaMovibles();
//					vistaCivil.setNombreArchivoImagen("pacman.jpg");
//					vistaCivil.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaCivil);
//					vistaMovibles.add(vistaCivil);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "BalaCohete") {
//					VistaMovibles vistaBalaCohete = new VistaMovibles();
//					vistaBalaCohete.setNombreArchivoImagen("pacman.jpg");
//					vistaBalaCohete.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaBalaCohete);
//					vistaMovibles.add(vistaBalaCohete);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "BalaLaser") {
//					VistaMovibles vistaBalaLaser = new VistaMovibles();
//					vistaBalaLaser.setNombreArchivoImagen("pacman.jpg");
//					vistaBalaLaser.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaBalaLaser);
//					vistaMovibles.add(vistaBalaLaser);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "BalaTorpedoRastreador") {
//					VistaMovibles vistaBalaTorpedoRastreador = new VistaMovibles();
//					vistaBalaTorpedoRastreador.setNombreArchivoImagen("pacman.jpg");
//					vistaBalaTorpedoRastreador.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaBalaTorpedoRastreador);
//					vistaMovibles.add(vistaBalaTorpedoRastreador);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				} else if (tipo == "BalaTorpedoSimple") {
//					VistaMovibles vistaBalaTorpedoSimple = new VistaMovibles();
//					vistaBalaTorpedoSimple.setNombreArchivoImagen("pacman.jpg");
//					vistaBalaTorpedoSimple.setPosicionable(espacioAereo.get(i));
//					controlador.agregarDibujable(vistaBalaTorpedoSimple);
//					vistaMovibles.add(vistaBalaTorpedoSimple);
//					controlador.agregarObjetoVivo(espacioAereo.get(i));
//				}
//			}
			
			controlador.setIntervaloSimulacion(7);

			controlador.comenzarJuego();
//			for (int i = 0; i < espacioAereo.size(); i++) {
//				if (espacioAereo.get(i).getActivo() == false) {
//					espacioAereo.remove(i);
//					controlador.removerDibujable(vistaMovibles.get(i));
//				}
//			}
		}
    	cronometro.terminar();
    	juego.getMision().misionCompleta();
    	new VentanaIngresoNombre(juego.getMision().getPuntajeDelJugador(), controlador);
	}
}
