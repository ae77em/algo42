package algo42.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algo42.vista.VentanaPrincipal;


public class GestionDeBotonRecords implements ActionListener{
	VentanaPrincipal ventana;
	
	public GestionDeBotonRecords(VentanaPrincipal ventana){
		this.ventana = ventana;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.ventana.removeAll();
		this.ventana.cargarPantallaRecords();
		this.ventana.repaint();	
	}
}