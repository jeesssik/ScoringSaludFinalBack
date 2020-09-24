package com.scoringsalud.app.application.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoringsalud.app.domain.user.Usuario;
import com.scoringsalud.app.domain.user.UsuarioRepository;

@Service
public class UsuarioService {
   
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Create operation
	public Usuario create(String mail, String nombre,String apellido, String edad) {
		Usuario user = new Usuario(mail, nombre, apellido, edad);
		return usuarioRepository.save(user);
	}
	//Retrieve operation
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	public Usuario getByName(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}
	//Update operation
	public Usuario update(String nombre, String apellido, String edad) {
		Usuario p = usuarioRepository.findByNombre(nombre);
		p.setApellido(apellido);
		p.setEdad(edad);
		return usuarioRepository.save(p);
	}
	//Delete operation
	public void deleteAll() {
		usuarioRepository.deleteAll();
	}
	public void delete(String mail) {
		Usuario p = usuarioRepository.findByMail(mail);
		usuarioRepository.delete(p);
	}
}