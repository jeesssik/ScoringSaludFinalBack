package com.scoringsalud.app.user.infrastructure;

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

import com.scoringsalud.app.exceptions.application.ApiNotFoundException;
import com.scoringsalud.app.exceptions.application.ApiProcessingException;
import com.scoringsalud.app.exceptions.application.ApiRequestException;
import com.scoringsalud.app.exceptions.application.ApiServerException;
import com.scoringsalud.app.user.application.UsuarioService;
import com.scoringsalud.app.user.domain.Usuario;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuario;

	@PostMapping(path = "/crearUsuario")
	public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario) {
		String usuarioCreado = this.usuario.crear(usuario).toString();
		return new ResponseEntity<>(usuarioCreado, HttpStatus.OK);
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
