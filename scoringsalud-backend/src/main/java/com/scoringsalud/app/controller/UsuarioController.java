package com.scoringsalud.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scoringsalud.app.model.Usuario;
import com.scoringsalud.app.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping("/crearUsuario")
	public String crearUsuario(@RequestBody Usuario usuario) {
		repository.save(usuario); //Antes hacer validación de datos
		return null; //Retornar success o error
	}
	
	@GetMapping(path="/getUsuario/{id}")
	public Optional<Usuario> buscarUsuario(@PathVariable String id) {
		return repository.findById(id); 
	}
}
