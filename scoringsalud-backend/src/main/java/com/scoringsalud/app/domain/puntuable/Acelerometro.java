package com.scoringsalud.app.domain.puntuable;

public class Acelerometro implements Medidor {
	protected int [] posicionUno;
	protected int [] posicionDos;
	
	public Acelerometro(int[] posicionUno, int[] posicionDos) {
		this.posicionUno = posicionUno;
		this.posicionDos = posicionDos;
	}
	
	@Override
	public String getTipo() {
		return "Acelerometro";
		
	}

	@Override
	public void setEstrategiaMedicion() {
		// TODO Auto-generated method stub
		
	}

}
