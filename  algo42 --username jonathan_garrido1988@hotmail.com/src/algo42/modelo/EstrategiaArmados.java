package algo42.modelo;

public abstract class EstrategiaArmados extends Estrategia {
        
	public abstract void usar(Nave nave, Mision tablero);

	public abstract void mover();
	
	public abstract void disparar();
}
