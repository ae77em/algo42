package algo42.modelo;

import java.util.ArrayList;

public class Flota {	

	private ArrayList<Punto> posicionesNaves = new ArrayList<Punto>();
	private ArrayList<Movible> naves = new ArrayList<Movible>();
	
	public void inicializarFlota(int numeroDeMision) {
		Nave naveActual;
		Guia naveGuia;
		int numeroAlAzar = (int) (Math.random()*93+1);
		int otroNumeroAlAzar = (int) (Math.random()*4+1);
		int otroOtroNumeroAlAzar;
		while ((numeroAlAzar >= 1)&&(numeroAlAzar <= 7)) {
			numeroAlAzar = (int) (Math.random()*93+1);
		}
		this.posicionesNaves.add(new Punto(numeroAlAzar, 12));
		naveGuia = new Guia();
		this.naves.add(naveGuia);
		
		for (int i = 1; i <= 5; i++) {
			if (i == 1) {
				this.posicionesNaves.add(new Punto(numeroAlAzar - 6, 2));
			} else {
				if (i == 2) {
					this.posicionesNaves.add(new Punto(numeroAlAzar - 3, 5));
				} else {
					if (i == 3) {
						this.posicionesNaves.add(new Punto(numeroAlAzar, 8));
					} else {
						if (i == 4) {
							this.posicionesNaves.add(new Punto(numeroAlAzar + 3, 5));
						} else {
							this.posicionesNaves.add(new Punto(numeroAlAzar + 6, 2));
						}
					}
				}
			}
			if (otroNumeroAlAzar == 1) {
				naveActual = new Caza();
			} else {
				otroOtroNumeroAlAzar = (int) (Math.random()*3+1);
				if (otroOtroNumeroAlAzar == 1) {
					naveActual = new Avioneta();
				} else {
					if (otroOtroNumeroAlAzar == 2) {
						naveActual = new Explorador();
					} else {
						naveActual = new Bombardero();
					}
				}
			}
			this.naves.add(naveActual);
			naveGuia.setNaveDeFlota(naveActual);
		}
	}
	
	public ArrayList<Movible> getNaves() {
		return this.naves;
	}

	public Punto getPosicionNave(int i) {
		return this.posicionesNaves.remove(i);
	}
}