package com.scoringsalud.app.infrastructure.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scoringsalud.app.application.exceptions.ApiNotFoundException;
import com.scoringsalud.app.application.exceptions.ApiProcessingException;
import com.scoringsalud.app.application.exceptions.ApiRequestException;
import com.scoringsalud.app.application.exceptions.ApiServerException;
import com.scoringsalud.app.application.user.UsuarioService;
import com.scoringsalud.app.domain.user.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuario;

	@PostMapping(path = "/crearUsuario")
	public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
		if (this.usuario.crear(usuario)!=null);
			return new ResponseEntity<>("Usuario creado", HttpStatus.OK);
	}

	@PutMapping(path = "/actualizarUsuario")
	public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		String mailUsuarioActualizado = this.usuario.actualizar(usuario).getMail();
		return new ResponseEntity<>("El usuario " + mailUsuarioActualizado + " se actualizo correctamente.",
				HttpStatus.OK);
	}

	@GetMapping(path = "/obtenerUsuario", produces = "application/json")
	public ResponseEntity<String> buscarUsuario(@RequestParam String mail) throws ApiRequestException, ApiServerException, ApiNotFoundException, ApiProcessingException {
		String usuarioEncontrado = this.usuario.getByMail(mail);
		return new ResponseEntity<>(usuarioEncontrado, HttpStatus.OK);
	}

	@DeleteMapping(path = "/eliminar")
	public ResponseEntity<String> delete(@RequestParam String mail) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		usuario.eliminar(mail);
		String successMessage = "Usuario: " + mail + " eliminado.";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}
}
