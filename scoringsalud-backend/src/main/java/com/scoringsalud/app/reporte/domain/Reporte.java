package com.scoringsalud.app.reporte.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
public class Reporte {
	
	protected String titulo;
	protected int puntos;
	protected int cantEjercicios;
	protected int horasDeSueño;
	protected int vasosDeAgua;
	protected int cantidadDePasos;

	
	
	public Reporte(String titulo, int puntos, int cantEjercicios, int horasDeSueño, int vasosDeAgua, int cantidadDePasos) {
		this.titulo = titulo;
		this.puntos = puntos;
		this.cantEjercicios = cantEjercicios;
		this.horasDeSueño = horasDeSueño;
		this.vasosDeAgua = vasosDeAgua;
		this.cantidadDePasos = cantidadDePasos;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public int getPuntos() {
		return puntos;
	}



	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}



	public int getCantEjercicios() {
		return cantEjercicios;
	}



	public void setCantEjercicios(int cantEjercicios) {
		this.cantEjercicios = cantEjercicios;
	}



	public int getHorasDeSueño() {
		return horasDeSueño;
	}



	public void setHorasDeSueño(int horasDeSueño) {
		this.horasDeSueño = horasDeSueño;
	}



	public int getVasosDeAgua() {
		return vasosDeAgua;
	}



	public void setVasosDeAgua(int vasosDeAgua) {
		this.vasosDeAgua = vasosDeAgua;
	}



	public int getCantidadDePasos() {
		return cantidadDePasos;
	}



	public void setCantidadDePasos(int cantidadDePasos) {
		this.cantidadDePasos = cantidadDePasos;
	}



	//public String toString() {
	//	return "Tipo: "+tipo+", Nombre:"+nombre+", Puntos:"+puntos+", Detalle:"+detalle+", User Mail:"+userMail;
	//}
	
	

}