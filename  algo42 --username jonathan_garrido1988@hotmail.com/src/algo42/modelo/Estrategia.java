package algo42.modelo;

public class Estrategia {
	
	public static final int DERECHA = 1;
	public static final int IZQUIERDA = -1;
	
	protected Nave nave;
	private Mision mision;
	
	// este metodo es igual para todos...
	public void indicarNaveYMision(Nave laNave,Mision elTablero){
		nave = laNave;
		mision = elTablero;
	}
	
	// este metodo se define en cada estrategia en particular
	public abstract void mover(){}
}
