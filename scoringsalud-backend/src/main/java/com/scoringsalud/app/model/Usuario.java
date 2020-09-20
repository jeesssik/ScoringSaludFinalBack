package com.scoringsalud.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Usuario")
public class Usuario {
	//ACÁ PONER TODOS LOS ATRIBUTOS DE USUARIO, EJ:
	@Id
	private String mail;
	private String dni;
	private String nombre;
	private String apellido;
	private String edad;
	private String puesto;
	private int puntos; //puntos totales? mensuales? diarios?
}
