package algo42.vista;

import java.awt.Button;
import java.awt.Label;

import algo42.controlador.GestionBotonAnteriorRecords;
import algo42.controlador.GestionDeBotonExit;
import algo42.controlador.GestionDeBotonRecords;
import algo42.controlador.GestionDeBotonStart;
import algo42.titiritero.ControladorJuego;
import algo42.titiritero.vista.Ventana;

public class VentanaPrincipal extends Ventana{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ancho;
	private int alto;
	private ControladorJuego controlador;

	public VentanaPrincipal(int ancho, int alto, ControladorJuego unControlador) {
		super(ancho, alto, unControlador);
		
		this.ancho = ancho;
		this.alto = alto;
		this.controlador = unControlador;
		
		this.setTitle("Algo42");
		this.setResizable(false);
		this.setVisible(true);
		this.cargarPantallaInicial();
	}
	
	public void cargarPantallaInicial(){
		Contenedor fondo = new Contenedor("fondo.jpg");
		fondo.setBounds(0, 0, this.ancho, this.alto);
		
		Label titulo = new Label();
		titulo.setBounds(this.ancho / 2 - 80, 0, 200, 200);
		titulo.setText("Algo42");
		titulo.setFont(new java.awt.Font("Arial", 0, 50));
		
		Button start = new Button("Start");		
		start.setBounds(this.ancho / 2 - 50, this.alto / 2 + 50, 105, 32);
		GestionDeBotonStart gbotonStart = new GestionDeBotonStart(controlador);
		start.addActionListener(gbotonStart);

		Button records = new Button("Record");
		records.setBounds(this.ancho / 2 - 50, this.alto / 2 + 100, 105, 32);
		GestionDeBotonRecords gbotonRecords = new GestionDeBotonRecords(this);
		records.addActionListener(gbotonRecords);

		Button exit = new Button("Exit");
		exit.setBounds(this.ancho / 2 - 50, this.alto / 2 + 150, 105, 32);
		GestionDeBotonExit gbotonExit = new GestionDeBotonExit();
		exit.addActionListener(gbotonExit);

		this.add(titulo);
		this.add(start);
		this.add(records);
		this.add(exit);
		this.add(fondo);
	}

	public void cargarPantallaRecords(){

		Contenedor fondo;

		fondo = new Contenedor("fondo.jpg");
		fondo.setBounds(0, 0, 640, 480);

		Button anterior = new Button("Anterior");
		anterior.setBounds(10, this.alto - 40, 100, 30);
		GestionBotonAnteriorRecords gbAnterior = new GestionBotonAnteriorRecords(this);
		anterior.addActionListener(gbAnterior);

		Label titulo = new Label();
		titulo.setBounds(this.ancho / 2 - 25, 0, 100, 100);
		titulo.setText("Records");
		titulo.setFont(new java.awt.Font("Arial", 0, 20));

		Label labelNombre = new Label();
		labelNombre.setText("      Nombre");
		labelNombre.setBounds(10, 30, 95, 20);
		labelNombre.setFont(new java.awt.Font("Arial",0,16));
			
		Label labelTiempo = new Label();
		labelTiempo.setText("Puntos");
		labelTiempo.setBounds(ancho - 100, 30, 95, 20);
		labelTiempo.setFont(new java.awt.Font("Arial",0,16));
		
		//Aca habria que agregar la parte de persistencia. La parte de leer los puntajes maximos.
			
		int cant = 10;
		Label [] labelRecordsNombre = new Label[cant];
		Label[] labelRecordsPuntaje = new Label[cant];
		for (int i = 0; i < cant; i++) {
			labelRecordsNombre[i] = new Label();
			labelRecordsNombre[i].setText((i + 1) + ".-------------------");
			labelRecordsNombre[i].setBounds(10, 60 + i * 30, 100, 25);
			labelRecordsNombre[i].setFont(new java.awt.Font("Arial",0,16));
			
			labelRecordsPuntaje[i] = new Label();
			labelRecordsPuntaje[i].setText("-----------");
			labelRecordsPuntaje[i].setBounds(ancho - 110, 60 + i * 30, 100, 25);
			labelRecordsPuntaje[i].setFont(new java.awt.Font("Arial", 0, 16));
			
			this.add(labelRecordsNombre[i]);
			this.add(labelRecordsPuntaje[i]);
		}
		
		this.add(labelTiempo);
		this.add(labelNombre);
		this.add(titulo);
		this.add(anterior);
		this.add(fondo);
	}
}