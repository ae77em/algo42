package algo42.vista;

import java.awt.Button;
import java.awt.Color;

import algo42.titiritero.ControladorJuego;
import algo42.titiritero.SuperficieDeDibujo;
import algo42.titiritero.vista.Panel;
import algo42.titiritero.vista.KeyPressedController;
import algo42.titiritero.vista.Ventana;


public class VentanaJuego extends Ventana {

	private ControladorJuego controladorJuego;
	private static final long serialVersionUID = 1L;
	private Panel panel;

	public VentanaJuego(ControladorJuego unControladorJuego, int tamanioAncho, int tamanioAlto){
		super(tamanioAlto, tamanioAncho, unControladorJuego);
		this.controladorJuego = unControladorJuego;
		this.addKeyListener(new KeyPressedController(controladorJuego));
		this.setTitle("Algo42");
		this.setSize(tamanioAncho, tamanioAlto);
		this.setResizable(false);
		this.panel = new Panel(tamanioAncho - 125, tamanioAlto, controladorJuego);
		this.add(panel);
		
	}
	
	public SuperficieDeDibujo getSuperficieDeDibujo() {
		return this.panel;
	}
}