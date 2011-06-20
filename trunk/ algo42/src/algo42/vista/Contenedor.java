package algo42.vista;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Contenedor extends Container{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// La imagen que queremos de fondo, un fichero .gif
	public ImageIcon icono;

	public Contenedor(String imagen){
		this.icono = new ImageIcon (imagen);		
	}
	
	public int getHeight(){
		return this.icono.getIconHeight();
	}
	
	public int getWidth(){
		return this.icono.getIconWidth();
	}
	
	public void cambiarImagen(String imagen){				
		this.icono.setImage(java.awt.Toolkit.getDefaultToolkit().getImage(imagen));
	}
	
	// Redefinición del método paint()
	public void paint (Graphics g){
		// Borramos todo y lo pintamos del color de fondo por defecto.
		Rectangle r = g.getClipBounds();
		g.setColor(this.getBackground());
		g.fillRect (r.x, r.y, r.width, r.height);

		// Pintamos la imagen
		g.drawImage (icono.getImage(), 0,0,this);

		// Hacemos que se pinten los botones dentro de este contenedor
		super.paint(g);
	}
	public ImageIcon getIm(){
		return this.icono;
	}
}