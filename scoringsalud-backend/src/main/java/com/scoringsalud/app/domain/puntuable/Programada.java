package com.scoringsalud.app.domain.puntuable;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString


public abstract class Programada extends Puntuable{

	
	protected  LocalDateTime horaInicio;
		
	public Programada(String nombre, int puntosOtorgables, LocalDateTime hora) {
		super(nombre, puntosOtorgables);
		this.horaInicio = hora;
	}
	
	public abstract LocalDateTime getHoraInicio();
	
	public abstract void setHoraInicio(LocalDateTime horaNueva);

}
