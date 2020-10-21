package com.scoringsalud.app.puntuable.domain;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Actividad extends Puntuable {

	protected @Getter @Setter boolean PosicionUnica;
	protected @Getter int repeticiones;
	protected @Getter @Setter int repeticionesRealizadas;
	protected Medidor[] medidores;
	
	public Actividad(String codigo, String nombre, int puntosOtorgables,boolean PosicionUnica,int repeticiones) {
		super(codigo, nombre,puntosOtorgables);
		this.PosicionUnica=PosicionUnica;
		setRepeticiones(repeticiones);
		setRepeticionesRealizadas(0);
	}
	public Actividad(String codigo, String nombre, int puntosOtorgables,boolean PosicionUnica,int repeticiones, Medidor[] medidores) {
		super(codigo, nombre,puntosOtorgables);
		this.PosicionUnica=PosicionUnica;
		setRepeticiones(repeticiones);
		setRepeticionesRealizadas(0);
		this.medidores = medidores;
	}


	@Override
	public void calcularPuntos() {
		
	};

	@Override
	public String getDetalle() {
		return null;
	};

	//getters / setters
	public void setRepeticiones(int repeticiones) {
		
		if(PosicionUnica) {
			this.repeticiones = repeticiones;
		}else {
			this.repeticiones = repeticiones + repeticiones;
		}
		
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public int getRepeticionesRealizadas() {
		return repeticionesRealizadas;
	}

	public void setRepeticionesRealizadas(int repeticionesRealizadas) {
		this.repeticionesRealizadas = repeticionesRealizadas;
	}

	public boolean isPosicionUnica() {
		return PosicionUnica;
	}

	public void setPosicionUnica(boolean posicionUnica) {
		PosicionUnica = posicionUnica;
	}
	public Medidor[] getMedidores() {
		return medidores;
	}
	public void setMedidores(Medidor[] medidores) {
		this.medidores = medidores;
	}


	
	
}
