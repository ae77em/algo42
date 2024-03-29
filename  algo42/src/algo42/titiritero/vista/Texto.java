package algo42.titiritero.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import algo42.titiritero.SuperficieDeDibujo;



/**
 * Sirve para abstraer al usuario de escribir Texto en la pantalla.
 * @see Font
 * @author mwaisgold@gmail.com
 *
 */
public abstract class Texto extends Figura {

	
	private Font fuente;
	
	public Texto(){
		setColor(Color.WHITE);
		setFuente("Serif",14);
	}
	
	public Texto(Color color, Font fuente){
		setColor(color);
		this.fuente = fuente;
	}
	
	public void setFuente(String fuente, int tama�o){
		this.fuente = new Font(fuente,Font.BOLD,tama�o);
	}
	
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = (Graphics)superfice.getBuffer();
		grafico.setColor(this.getColor());
		grafico.setFont(fuente);
		grafico.drawString(getTexto(), this.getPosicionable().getX(), this.getPosicionable().getY());
	}

	protected abstract String getTexto();

}
