package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo42.vista.VentanaPartidaGuardada;


public class GestionDeBotonStart implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		new VentanaPartidaGuardada();
	}
}