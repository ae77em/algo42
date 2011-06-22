package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo42.titiritero.ControladorJuego;
import algo42.vista.VentanaPartidaGuardada;

public class GestionDeBotonStart implements ActionListener {
	
	private ControladorJuego controlador;
	
	public GestionDeBotonStart(ControladorJuego unControlador){
		this.controlador = unControlador;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		new VentanaPartidaGuardada(controlador);
	}
}