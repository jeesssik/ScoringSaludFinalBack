package com.scoringsalud.app.domain.puntuable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tecnica extends Programada{

	private int ciclosMaximos;
	private int ciclosRealizados;
	private int pausasRealizadas;
	private int tiempoCiclo;
	private int tiempoPausaCorta;
	private int tiempoPausaLarga;
	private ArrayList<Regular> regulares;
	
	
	public Tecnica(String nombre, int puntosOtorgables,int ciclosMaximos,int tiempoCiclo,int tiempoPausaCorta,int tiempoPausaLarga, LocalDateTime horaInicio) {
		super(nombre, puntosOtorgables, horaInicio);
		this.ciclosMaximos = ciclosMaximos;
		this.ciclosRealizados = 0;
		this.pausasRealizadas = 0;
		this.tiempoCiclo = tiempoCiclo;
		this.tiempoPausaCorta= tiempoPausaCorta;
		this.tiempoPausaLarga =tiempoPausaLarga;
		this.regulares = new ArrayList<Regular>();
		
	}

	
	public int getCiclosMaximos() {
		return ciclosMaximos;
	}

	public void setCiclosMaximos(int ciclosMaximos) {
		this.ciclosMaximos = ciclosMaximos;
	}

	public int getCiclosRealizados() {
		return ciclosRealizados;
	}

	public void setCiclosRealizados(int ciclosRealizados) {
		this.ciclosRealizados = ciclosRealizados;
	}

	public int getPausasRealizadas() {
		return pausasRealizadas;
	}

	public void setPausasRealizadas(int pausasRealizadas) {
		this.pausasRealizadas = pausasRealizadas;
	}

	public int getTiempoCiclo() {
		return tiempoCiclo;
	}

	public void setTiempoCiclo(int tiempoCiclo) {
		this.tiempoCiclo = tiempoCiclo;
	}

	public int getTiempoPausaCorta() {
		return tiempoPausaCorta;
	}

	public void setTiempoPausaCorta(int tiempoPausaCorta) {
		this.tiempoPausaCorta = tiempoPausaCorta;
	}

	public int getTiempoPausaLarga() {
		return tiempoPausaLarga;
	}

	public void setTiempoPausaLarga(int tiempoPausaLarga) {
		this.tiempoPausaLarga = tiempoPausaLarga;
	}

	public ArrayList<Regular> getRegulares() {
		return regulares;
	}

	public void setRegulares(ArrayList<Regular> regulares) {
		this.regulares = regulares;
	}

	public void addRegulares(Regular r) {
		this.regulares.add((Regular) r);
	}
	
 	private int calcularPuntosRegulares() {
	int acumulador;
	acumulador =0;
		for(Regular r : regulares) {
			acumulador += (r.getPuntosObtenidos())*2;
		}
	return acumulador;
	}
	
	@Override
	public void calcularPuntos() {
		setPuntosObtenidos((getPuntosOtorgables()/getCiclosMaximos())*getCiclosRealizados() + calcularPuntosRegulares());
		
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

}
