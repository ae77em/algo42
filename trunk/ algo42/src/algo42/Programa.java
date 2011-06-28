package algo42;

import algo42.titiritero.ControladorJuego;
import algo42.vista.VentanaPrincipal;

public class Programa {

    private static ControladorJuego controlador = new ControladorJuego();
    private static int ancho = 400;
    private static int alto = 400;
    
    public static void main(String[] args) {        
            new VentanaPrincipal(ancho, alto, controlador);
    }
}
