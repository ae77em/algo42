package algo42.vista;

import algo42.titiritero.SuperficieDeDibujo;
import algo42.titiritero.ControladorJuego;
import algo42.titiritero.vista.Ventana;


public class VentanaJuego extends Ventana{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Contenedor panel;

	public VentanaJuego(int ancho, int alto, ControladorJuego unControlador) {
		super(ancho, alto, unControlador);
		
		this.setTitle("Algo42");
		this.setSize(ancho, alto);
		this.setResizable(false);
		this.setVisible(true);
		Contenedor panel = new Contenedor("fondo.jpg");
		this.add(panel);
		
	}
	
	public SuperficieDeDibujo getSuperficieDeDibujo() {
		return (SuperficieDeDibujo) this.panel;
	}
}
