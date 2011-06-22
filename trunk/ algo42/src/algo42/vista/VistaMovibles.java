package algo42.vista;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import algo42.titiritero.SuperficieDeDibujo;
import algo42.titiritero.vista.Imagen;

public class VistaMovibles extends Imagen {

	private BufferedImage imagen;

	public VistaMovibles() {

	}

	public void dibujar(SuperficieDeDibujo superficeDeDibujo) {
		this.imagen = super.getImagen();
		Graphics2D grafico = (Graphics2D) superficeDeDibujo.getBuffer();
		grafico.drawImage(this.imagen, super.getPosicionable().getX(), super.getPosicionable().getY(), null);
	}
}