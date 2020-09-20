package com.scoringsalud.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.scoringsalud.app.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> { // donde dice String es el tipo de la PK de Usuario(wrapper, porque debe ser un Object)

}
