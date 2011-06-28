package algo42.modelo;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro extends TimerTask {

	private int tiempoEnSegundos;
	private Timer timer;
	public Cronometro() {
		this.tiempoEnSegundos = 0;
		this.timer = new Timer();
	}
	
	public void comenzar() {
		this.timer.schedule(this, 0, 1000);
	}
	
	public void run() {
		this.tiempoEnSegundos = this.tiempoEnSegundos + 1;
	}
	
	public int getTiempoEnSegundos() {
		return this.tiempoEnSegundos;
	}
	
	public void terminar() {
		this.timer.cancel();
	}
}