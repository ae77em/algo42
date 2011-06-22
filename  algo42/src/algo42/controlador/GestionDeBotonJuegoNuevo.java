package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo42.ProgramaJugable;
import algo42.titiritero.ControladorJuego;
import algo42.vista.VentanaJuego;
import algo42.vista.VentanaPartidaGuardada;


public class GestionDeBotonJuegoNuevo implements ActionListener{
	
	private VentanaPartidaGuardada ventanaActual;
	private ProgramaJugable programa;
	private ControladorJuego controlador;
	
	public GestionDeBotonJuegoNuevo(VentanaPartidaGuardada ventana, ControladorJuego unControlador){
		this.ventanaActual = ventana;
		this.controlador = unControlador;
		this.programa = new ProgramaJugable(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.ventanaActual.cerrar();
		VentanaJuego ventanaNueva = new VentanaJuego(500, 500, controlador);
		controlador.setSuperficieDeDibujo(ventanaNueva.getSuperficieDeDibujo());
//		this.programa.comenzarJuego();
	}
}
