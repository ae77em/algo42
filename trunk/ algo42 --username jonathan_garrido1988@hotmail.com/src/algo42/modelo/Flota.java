package algo42.modelo;

import java.util.ArrayList;

public class Flota {
	
	public ArrayList<Movible> getNaves(){
		ArrayList<Movible> naves = new ArrayList<Movible>();
		
		int numeroAlAzar = 0;
		//Elijo un numero al azar para ver que naves vienen
		numeroAlAzar = (int) (Math.random()*3+1);
		
		int otroNumeroAlAzar = 0;
		//Elijo otro numero al azar para ver si llamo a los cazas
		otroNumeroAlAzar = (int) (Math.random()*6+1);
		
		int contadorDeNaves = 0;
		while ((contadorDeNaves <=5) && (otroNumeroAlAzar != 6)){
			if(numeroAlAzar == 1){
				naves.add(0, new Avioneta());
				contadorDeNaves = contadorDeNaves + 1;
			}
			else if(numeroAlAzar == 2){
				naves.add(0, new Bombardero());
				contadorDeNaves = contadorDeNaves + 1;
			}
			else if(numeroAlAzar == 3){
				naves.add(0, new Explorador());
				contadorDeNaves = contadorDeNaves + 1;
			}
		}
		if (otroNumeroAlAzar == 6){
			while(contadorDeNaves <= 5){
				naves.add(0, new Caza());
				contadorDeNaves = contadorDeNaves + 1;
			}
		}
		naves.add(new Guia());
		return naves;
	}

	public Punto getPosicionNave(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}