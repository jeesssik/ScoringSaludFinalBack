package com.scoringsalud.app.application.user;

import java.io.FileNotFoundException;
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
	public Usuario update(Usuario usuario) throws FileNotFoundException {
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
	public void deleteAll() {
		usuarioRepository.deleteAll();
	}
	//Eliminar usuario
	public void delete(String mail) throws FileNotFoundException {
		Usuario user = usuarioRepository.findByMail(mail.trim());
		if (user == null) {
			throw new FileNotFoundException("El usuario "+ mail+" no fue encontrado, verifique su mail.");
		}
		usuarioRepository.delete(user);
	}
}