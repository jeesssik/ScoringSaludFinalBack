package com.scoringsalud.app.puntuable.domain;

public class Acelerometro extends Medidor {
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


}
