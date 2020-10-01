package com.scoringsalud.app.domain.puntuable;

public class Estiramiento extends Regular{

	public Estiramiento(String nombre, int puntosOtorgables, boolean PosicionUnica, int repeticiones) {
		super(nombre, puntosOtorgables, PosicionUnica, repeticiones);
		
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
