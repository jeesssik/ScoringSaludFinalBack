package com.scoringsalud.app.puntuable.domain;

public class Estiramiento extends Regular{

	public Estiramiento(String codigo, String nombre, int puntosOtorgables, boolean PosicionUnica, int repeticiones) {
		super(codigo, nombre, puntosOtorgables, PosicionUnica, repeticiones);
		
	}

	
	@Override
	public void calcularPuntos() {
		
		if(getRepeticiones()==getRepeticionesRealizadas()) {
			setPuntosObtenidos(puntosOtorgables);
		}else {
			setPuntosObtenidos(puntosOtorgables/getRepeticionesRealizadas());
		} 
	}
	

	@Override
	public String getDetalle() {
		
		return "Estiramiento: "+ nombre + " realizada el dia: "+ fecha + " puntos obtenidos: " + puntosObtenidos;
	}

}
