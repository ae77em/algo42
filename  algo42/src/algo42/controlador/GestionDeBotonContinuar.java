package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo42.vista.VentanaPartidaGuardada;


public class GestionDeBotonContinuar implements ActionListener{
	
	private VentanaPartidaGuardada ventana;
	
	public GestionDeBotonContinuar(VentanaPartidaGuardada ventana){
		this.ventana = ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.ventana.cerrar();
	}
}
