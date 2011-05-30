package algo42.modelo;

public abstract class Objeto implements Movible {

	private Mision tablero;
	private Punto posicion;
	protected boolean activo;
	private int equipo;
	private Direccion direccion;
	private int velocidad;
	private int tamanio;
	
	public Objeto () {
		this.activo = false;
		this.equipo = 0;
		this.tamanio = 1;
		this.direccion = new Abajo();
		this.velocidad = 1;
		this.posicion = new Punto(0, 0);
	}
	
	public void activar (Mision tablero, Punto posicion) {
		this.activo = true;
		this.tablero = tablero;
		this.posicion = posicion;
	}
	
	public void destruir() {
		this.activo = false;
	}
	
	public void mover() {
		if (this.activo = true) {
			if (this.posicion.getY() >= 100) {
				this.destruir();
			} else {
				this.direccion.trasladar(this, tablero);
			}
		}
	}
	
	public abstract void consumirPor (Algo42 algo42);
	
	@Override
	public Punto getPosicion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getOcupado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPosicion(Punto posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activarMisionEnPosicion(Mision mision, Punto posicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRadio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
