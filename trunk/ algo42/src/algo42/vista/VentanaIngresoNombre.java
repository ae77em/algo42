package algo42.vista;

import java.awt.Button;
import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import algo42.titiritero.ControladorJuego;
import algo42.titiritero.vista.Ventana;


public class VentanaIngresoNombre extends Ventana {
	
	private static final long serialVersionUID = 1L;
	TextField texto = new TextField("Ingrese su nombre");
//	private Resultados resultados;
	private String puntos;
	private boolean termino;

	public VentanaIngresoNombre(int puntos, ControladorJuego unControlador) {
		super(200, 200, unControlador);
		this.termino=false;
//		this.resultados=resultados;
		this.setBackground(Color.black);
		this.puntos=Integer.toString(puntos);
		Panel panelAlto = new Panel();
		Label label1 = new Label("Tu score es", Label.CENTER);
		Label label2 = new Label(this.puntos, Label.CENTER);
		label1.setForeground(Color.WHITE);
		label2.setForeground(Color.WHITE);
		panelAlto.add("West", label1);
		panelAlto.add("East", label2);
		add("North", panelAlto);
		setResizable(false);
		Panel panelBajo = new Panel();
		panelBajo.add(texto);
		panelBajo.add(new Button("Aceptar"));
		add("South",panelBajo);
		
		setTitle("Nombre");
	    pack();
		setVisible(true);	
	}
	
	public boolean action(Event evt, Object obj) {
		String nombre = texto.getText();
		System.out.println(nombre.indexOf("Ingrese su nombre"));
		if ((nombre == "") | (nombre.indexOf("Ingrese su nombre") == 0)) {
			nombre = "----------";
		}
//		try {
//			this.resultados.guardarPuntaje(nombre, Integer.parseInt(puntos));
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		this.termino = true;
		dispose();
		return true;
	}

	public boolean termino() {
		return this.termino;
	}
}