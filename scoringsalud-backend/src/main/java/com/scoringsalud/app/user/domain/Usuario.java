package com.scoringsalud.app.user.domain;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.scoringsalud.app.puntuable.domain.Puntuable;

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
	 @DBRef(db="puntuable")
	private ArrayList<Puntuable> puntuables = new ArrayList<Puntuable>();
	
	public Usuario(String mail, String nombre, String apellido, String edad) {
		this.mail = mail;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;	
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public ArrayList<Puntuable> getPuntuables() {
		return this.puntuables;
	}
	public void addPuntuable(Puntuable puntuable) {
		this.puntuables.add(puntuable);
	}
	
	public String toString() {
		return "Nombre: "+nombre+", Apellido:"+apellido+", Edad:"+edad;
	}
	
}
