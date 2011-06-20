package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo42.vista.VentanaPrincipal;


public class GestionBotonAnteriorRecords implements ActionListener{

	VentanaPrincipal ventana;
		
	public GestionBotonAnteriorRecords(VentanaPrincipal ventana){
		this.ventana = ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
			
		this.ventana.removeAll();
		this.ventana.cargarPantallaInicial();
		this.ventana.repaint();
	}
}