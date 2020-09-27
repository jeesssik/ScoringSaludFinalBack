package com.scoringsalud.app.application.user;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoringsalud.app.domain.user.Usuario;
import com.scoringsalud.app.domain.user.UsuarioRepository;

@Service
public class UsuarioService {
   
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Create operation
	public Usuario crear(Usuario user) throws NullPointerException {
		if (user.getMail() == null) {
			throw new NullPointerException("El campo mail no puede estar vacio para crear el usuario");
		}
		return usuarioRepository.save(user);
	}
	//Retrieve operation
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	//Retrieve operation
	public String getByMail(String mail) throws NullPointerException{
		if (mail == null) {
			throw new NullPointerException("Mail no puede ser nulo.");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(usuarioRepository.findByMail(mail));
		} catch (JsonProcessingException e) {
			return e.toString();
		}
	}
	public Usuario getByName(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}
	//Update operation
	public Usuario actualizar(Usuario usuario) throws FileNotFoundException {
		Usuario user = usuarioRepository.findByMail(usuario.getMail());
		if (user == null) {
			throw new FileNotFoundException("El usuario "+ usuario.getMail()+" no fue encontrado, verifique su mail.");
		}
		user.setNombre(usuario.getNombre());
		user.setApellido(usuario.getApellido());
		user.setEdad(usuario.getEdad());
		user.setDni(usuario.getDni());
		return usuarioRepository.save(user);
	}
	//Delete operation
	public void borrarTodos() {
		usuarioRepository.deleteAll();
	}
	//Eliminar usuario
	public void eliminar(String mail) throws FileNotFoundException {
		Usuario user = usuarioRepository.findByMail(mail.trim());
		if (user == null) {
			throw new FileNotFoundException("El usuario "+ mail+" no fue encontrado, verifique su mail.");
		}
		usuarioRepository.delete(user);
	}
}