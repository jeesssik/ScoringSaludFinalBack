package com.scoringsalud.app.puntuable.domain;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@ToString
@Document(collection = "Puntuable")
public class Puntuable {
	@Id
	private String id;
	
	protected String tipo;
	protected String nombre;
	protected int puntos;
	protected int unidades;
	protected LocalDateTime fecha;
	protected String detalle;
	protected String userMail;

	
	public Puntuable(String tipo, String nombre, int unidades, String detalle, String userMail) {
		this.tipo = tipo;
		this.nombre = nombre;
		int puntosOtorgados;
		if(tipo.equals("Ejercicio")) {
			puntosOtorgados = 50;
		}else if(tipo.equals("Pomodoro")) {
			puntosOtorgados = 100;
		}else {
			puntosOtorgados = 0;
		}
		this.puntos = puntosOtorgados;
		this.unidades = unidades;
		this.detalle = detalle;
		this.userMail = userMail;
		fecha = LocalDateTime.now();
	}


	//getters / setters

	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getPuntos() {
		return puntos;
	}



	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}



	public LocalDateTime getFecha() {
		return fecha;
	}



	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}



	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public String getUserMail() {
		return userMail;
	}


	public void setUsermail(String userMail) {
		this.userMail = userMail;
	}


	public String getDetalle() {
		return detalle;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public String toString() {
		return "Tipo: "+tipo+", Nombre:"+nombre+", Puntos:"+puntos+", Detalle:"+detalle+", User Mail:"+userMail;
	}
	
	

}
