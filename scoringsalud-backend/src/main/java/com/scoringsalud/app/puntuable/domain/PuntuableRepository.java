package com.scoringsalud.app.puntuable.domain;


import org.springframework.data.mongodb.repository.MongoRepository;


public interface PuntuableRepository extends MongoRepository<Puntuable, String> { // donde dice String es el tipo de la PK de Usuario(wrapper, porque debe ser un Object)
	
	public Puntuable findByCodigo(String nombre);
	
}