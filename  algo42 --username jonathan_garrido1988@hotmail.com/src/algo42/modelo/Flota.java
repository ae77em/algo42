package algo42.modelo;

import java.util.ArrayList;

public class Flota {
	
	private ArrayList<Movible> avionetas, cazas, bombarderos, exploradores, naveMadre;
	private int topeAvioneta, topeCaza, topeBombardero, topeExplorador;

	
	public void inicializarFlota(int numeroDeMision) {
		this.avionetas = new ArrayList<Movible>();
		this.cazas = new ArrayList<Movible>();
		this.bombarderos = new ArrayList<Movible>();
		this.exploradores = new ArrayList<Movible>();
		this.naveMadre = new ArrayList<Movible>();
		
		this.naveMadre.add(0, new Guia());
		
		if(numeroDeMision == 1){
			this.topeAvioneta = 220;
			this.topeCaza = 28;
			this.topeBombardero = 0;
			this.topeExplorador = 6;
		}
		else{
			this.topeAvioneta = (220 + (numeroDeMision * 10));
			this.topeCaza = (28 + (numeroDeMision * 5));
			this.topeBombardero = numeroDeMision;
			this.topeExplorador = (6 + (numeroDeMision * 2));
		}
		int i = 0;
		while(i <= this.topeAvioneta){this.avionetas.add(0, new Avioneta());}
		while(i <= this.topeCaza){this.cazas.add(0, new Caza());}
		while(i <= this.topeBombardero){this.bombarderos.add(0, new Bombardero());}
		while(i <= this.topeExplorador){this.exploradores.add(0, new Explorador());}	
	}
	
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
				if(this.avionetas.size() >= 1){
					naves.add(0, this.avionetas.remove(0));
					contadorDeNaves = contadorDeNaves + 1;
				}
			}
			else if(numeroAlAzar == 2){
				if(this.bombarderos.size() >= 1){
					naves.add(0, this.bombarderos.remove(0));
					contadorDeNaves = contadorDeNaves + 1;
				}
			}
			else if(numeroAlAzar == 3){
				if(this.exploradores.size() >= 1){
					naves.add(0, this.exploradores.remove(0));
					contadorDeNaves = contadorDeNaves + 1;
				}
			}
		}
		if (otroNumeroAlAzar == 6){
			if (this.cazas.size() >= 5){
				while(contadorDeNaves <= 5){
					naves.add(0, this.cazas.remove(0));
					contadorDeNaves = contadorDeNaves + 1;
				}
			}
			else{
				naves.add(0, this.cazas.remove(0));
				contadorDeNaves = contadorDeNaves + 1;
			}
		}
		naves.add(this.naveMadre.remove(0));
		return naves;
	}

	public Punto getPosicionNave(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}