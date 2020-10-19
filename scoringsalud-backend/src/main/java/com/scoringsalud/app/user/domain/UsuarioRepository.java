package com.scoringsalud.app.user.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> { // donde dice String es el tipo de la PK de Usuario(wrapper, porque debe ser un Object)
	public Usuario findByMail(String mail);
	public Usuario findByNombre(String nombre);
	public List<Usuario> findByEdad(String edad);

}
