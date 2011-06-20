package algo42.vista;

import java.awt.Button;
import java.awt.Color;
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

	public VentanaPrincipal(int ancho, int alto, ControladorJuego unControlador) {
		super(ancho, alto, unControlador);
		
		this.ancho = ancho;
		this.alto = alto;
		
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
		GestionDeBotonStart gbotonStart = new GestionDeBotonStart();
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
		anterior.setBounds(10, this.alto - 50, 100, 30);
		GestionBotonAnteriorRecords gbAnterior = new GestionBotonAnteriorRecords(this);
		anterior.addActionListener(gbAnterior);

		Label titulo = new Label();
		titulo.setBounds(this.ancho / 2 - 25, 0, 100, 100);
		titulo.setText("Records");
		titulo.setFont(new java.awt.Font("Arial", 0, 20));

		Label[] label = new Label[12];
		for (int i = 0; i < 10; i++){
			label[i] = new Label();
			label[i].setForeground(Color.WHITE);
			this.add(label[i]);
			label[i].setText(" "+(i+1));
			label[i].setBounds(200, 60 + i * 30, 95, 155);
			label[i].setFont(new java.awt.Font("Arial",0,20));
		}
		
		label[10] = new Label();
		label[10].setForeground(Color.WHITE);
		this.add(label[10]);
		label[10].setText("Nombre");
		label[10].setBounds(250, 30, 95, 155);
		label[10].setFont(new java.awt.Font("Arial",0,20));
			
		label[11] = new Label();
		label[11].setForeground(Color.WHITE);
		this.add(label[11]);
		label[11].setText("Tiempo");
		label[11].setBounds(350, 30, 95, 155);
		label[11].setFont(new java.awt.Font("Arial",0,20));
		
		
//		String archivoRecords = "Test";
//		
//		Persistencia persistencia = new Persistencia();
//		
//		Record records = (Record)persistencia.leer(archivoRecords);
//			
//		int cant = records.getCantidadDeRecords();
//		String [][] todo = new String[2][cant];
//		todo = records.obtenerTodos();
//		Label [][] labelRecords = new Label[2][cant];
//			
//		for (int j = 0; j < cant; j++) {
//			for (int i = 0; i < 2; i++) {								
//				labelRecords[i][j] = new Label();
//				labelRecords[i][j].setForeground(Color.WHITE);
//				this.add(labelRecords[i][j]);
//				labelRecords[i][j].setText(todo[i][j]);
//				labelRecords[i][j].setBounds(250 + i * 100, 60 + j * 30, 95, 155);
//				labelRecords[i][j].setFont(new java.awt.Font("Arial",0,20));
//			}
//		}

		this.add(titulo);
		this.add(anterior);
		this.add(fondo);
	}
}