package algo42.modelo;

public abstract class Estrategia {
        
	protected Nave nave;
	protected Mision tablero;
	protected Direccion direccion;
	
	public abstract void usar(Nave nave, Mision tablero);
	
	public abstract void mover();
}
