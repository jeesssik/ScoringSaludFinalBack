package com.scoringsalud.app.domain.puntuable;

import java.time.LocalDateTime;

public class Notificacion extends Programada {
	
	private Estado estado;
	private int cantRepeticiones;
	
	



	public Notificacion (String nombre, int puntosOtorgables, LocalDateTime horaInicio, int cantRepeticiones) {
		super(nombre, puntosOtorgables, horaInicio);
		this.estado =  new Postergada();
	
	}



	@Override
	public void calcularPuntos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDetalle() {
		
		return null;
	}

	@Override
	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}
	
	@Override
	public void setHoraInicio(LocalDateTime horaNueva) {
		
		this.horaInicio = horaNueva;
	}

	
	public int getCantRepeticiones() {
		return cantRepeticiones;
	}


	public void setCantRepeticiones(int cantRepeticiones) {
		this.cantRepeticiones = cantRepeticiones;
	}




	

	
}
