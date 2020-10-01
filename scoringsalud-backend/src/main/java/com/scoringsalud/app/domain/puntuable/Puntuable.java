package com.scoringsalud.app.domain.puntuable;


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
public abstract class Puntuable {
	
	@Id
	protected  String nombre;
	protected  int puntosOtorgables;
	protected  int puntosObtenidos;
	protected  LocalDateTime fecha;
	protected  String detalle;

	
	public Puntuable(String nombre, int puntosOtorgables) {
		this.nombre = nombre;
		this.puntosOtorgables = puntosOtorgables;
		fecha = LocalDateTime.now();
	}
	
	
	
	public abstract void calcularPuntos();
	public abstract String getDetalle();
	
	//getters / setters
	public int getPuntosOtorgables() {
		return puntosOtorgables;
	}

	public void setPuntosOtorgables(int puntosOtorgables) {
		this.puntosOtorgables = puntosOtorgables;
	}

	public int getPuntosObtenidos() {
		return puntosObtenidos;
	}

	public void setPuntosObtenidos(int puntosObtenidos) {
		this.puntosObtenidos = puntosObtenidos;
	}

}
