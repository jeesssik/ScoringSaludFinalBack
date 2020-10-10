package com.scoringsalud.app.puntuable.domain;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Regular extends Puntuable {

	protected @Getter @Setter boolean PosicionUnica;
	protected @Getter int repeticiones;
	protected @Getter @Setter int repeticionesRealizadas;
	protected ArrayList<Medidor> medidores;
	
	public Regular(String codigo, String nombre, int puntosOtorgables,boolean PosicionUnica,int repeticiones) {
		super(codigo, nombre,puntosOtorgables);
		this.PosicionUnica=PosicionUnica;
		setRepeticiones(repeticiones);
		setRepeticionesRealizadas(0);
	}

	@Override
	public abstract void calcularPuntos(); 

	@Override
	public abstract String getDetalle();

	//getters / setters
	public void setRepeticiones(int repeticiones) {
		if(PosicionUnica) {
			this.repeticiones=repeticiones;
		}else {
			this.repeticiones=repeticiones*2;
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

	public ArrayList<Medidor> getMedidores() {
		return medidores;
	}

	public void setMedidores(ArrayList<Medidor> medidores) {
		this.medidores = medidores;
	}
	
	
}
