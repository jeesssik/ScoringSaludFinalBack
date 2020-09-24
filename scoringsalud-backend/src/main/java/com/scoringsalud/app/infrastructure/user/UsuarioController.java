package com.scoringsalud.app.infrastructure.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scoringsalud.app.application.user.UsuarioService;
import com.scoringsalud.app.domain.user.Usuario;
import com.scoringsalud.app.domain.user.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioService usuario;
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(@RequestBody Usuario usuario) {
		repository.save(usuario); //Antes hacer validación de datos
		return null; //Retornar success o error
	}
	
	@RequestMapping("/create")
	public String create(@RequestParam String mail, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String edad) {
		try {
			Usuario p = usuario.create(mail, nombre, apellido, edad);
			return p.toString();
		}catch(NullPointerException e) {
			return e.toString();
		}
		
	}
	
	@GetMapping(path="/getUsuario/{id}")
	public Optional<Usuario> buscarUsuario(@PathVariable String id) {
		return repository.findById(id); 
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String mail) {
		usuario.delete(mail);
		return "Deleted "+mail;
	}
}
