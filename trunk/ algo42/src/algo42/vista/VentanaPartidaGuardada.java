package algo42.vista;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import algo42.controlador.GestionDeBotonContinuar;
import algo42.controlador.GestionDeBotonJuegoNuevo;
import algo42.titiritero.ControladorJuego;

public class VentanaPartidaGuardada extends Frame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ancho = 500;
	private int alto = 200;
	
	public VentanaPartidaGuardada(ControladorJuego unControlador){
		
		setSize(ancho, alto);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
		
		//Redefino este método para que se cierre solo esta ventana y no todo el programa
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		this.setTitle("Algo42 - Partida guardada");
		this.setResizable(false);
		this.setVisible(true);
	
		Contenedor fondo = new Contenedor("fondo.jpg");
		fondo.setBounds(0, 0, ancho, alto);
	
		Label label1 = new Label();
		label1.setBounds(10, this.alto - 150, 1000, 30);
		label1.setText("Se ha encontrado una partida guardada.");
		label1.setFont(new java.awt.Font("Arial", 0, 14));
	
		Label label2 = new Label();
		label2.setBounds(10, this.alto - 125, 1000, 30);
		label2.setText("¿Desea continuarla o quiere comenzar un juego nuevo ?");
		label2.setFont(new java.awt.Font("Arial", 0, 14));
	
		Button juegoNuevo = new Button("Juego nuevo");		
		juegoNuevo.setBounds(10, this.alto / 2 + 50, 100, 30);
		GestionDeBotonJuegoNuevo gbotonJuegoNuevo = new GestionDeBotonJuegoNuevo(this, unControlador);
		juegoNuevo.addActionListener(gbotonJuegoNuevo);
		
		Button continuar = new Button("Continuar");
		continuar.setBounds(this.ancho - 120, this.alto / 2 + 50, 100, 30);
		GestionDeBotonContinuar gbotonContinuar = new GestionDeBotonContinuar(this);
		continuar.addActionListener(gbotonContinuar);
		
		this.add(label1);
		this.add(label2);
		this.add(juegoNuevo);
		this.add(continuar);
		this.add(fondo);
	}
	
	public void cerrar(){
		dispose();
	}
}
