package com.scoringsalud.app.infrastructure.user;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scoringsalud.app.application.user.UsuarioService;
import com.scoringsalud.app.domain.user.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuario;
	
	@PostMapping(path="/crearUsuario")
	public @ResponseBody String crearUsuario(@RequestBody Usuario usuario) {
		try {
			return this.usuario.crear(usuario).toString(); //Antes hacer validación de datos
		}catch(NullPointerException e) {
			return e.toString();
		}
		
	}
	
	@PutMapping(path="/actualizarUsuario")
	public @ResponseBody String actualizarUsuario(@RequestBody Usuario usuario) {
		try {
			return "El usuario "+this.usuario.actualizar(usuario).getMail()+" se actualizo correctamente."; //Retornar success o error
		}
		catch(FileNotFoundException e) {
			return e.toString();
		}
	}
		
	@GetMapping(path="/obtenerUsuario", produces="application/json")
	public @ResponseBody String buscarUsuario(@RequestParam String mail) {
		try {
			return this.usuario.getByMail(mail); 
		}
		catch(NullPointerException e) {
			return e.toString();
		}
	}
	
	@DeleteMapping(path="/eliminar")
	public @ResponseBody String delete(@RequestParam String mail) {
		try {
			usuario.eliminar(mail);
			return "Usuario: "+mail+" eliminado.";
		}catch(FileNotFoundException e) {
			return e.toString();
		}
	}
}
