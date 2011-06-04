package algo42.modelo;

public abstract class Estrategia {
        
	private Nave nave;
	private Mision tablero;
	private Direccion direccion;
	
	public abstract void usar(Nave nave, Mision tablero);
	
	public abstract void mover();
	
	public void setTablero(Mision tablero) {
		this.tablero = tablero;
	}
	
	public Mision getTablero() {
		return this.tablero;
	}
	
	public void setNave(Nave nave) {
		this.nave = nave;
	}
	
	public Nave getNave() {
		return this.nave;
	}
	
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public Direccion getDireccion() {
		return this.direccion;
	}
}