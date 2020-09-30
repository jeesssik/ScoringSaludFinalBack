package com.scoringsalud.app.application.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoringsalud.app.application.exceptions.ApiNotFoundException;
import com.scoringsalud.app.application.exceptions.ApiProcessingException;
import com.scoringsalud.app.application.exceptions.ApiRequestException;
import com.scoringsalud.app.application.exceptions.ApiServerException;
import com.scoringsalud.app.domain.user.Usuario;
import com.scoringsalud.app.domain.user.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Create operation
	public Usuario crear(Usuario user) throws ApiRequestException, ApiServerException {
		if (user.getMail() == null || user.getMail().isEmpty()) {
			throw new ApiRequestException("El campo mail no puede estar vacio para crear el usuario");
		}
		Usuario usuarioEncontrado = usuarioRepository.findByMail(user.getMail().trim());
		if (!(usuarioEncontrado == null)) {
			return usuarioEncontrado;
		}
		try {
			usuarioRepository.save(user);
		} catch (Exception e) {
			throw new ApiServerException("Error al intentar crear el usuario");
		}
		return usuarioRepository.save(user);
	}

	// Retrieve operation
	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	// Retrieve operation
	public String getByMail(String mail)
			throws ApiRequestException, ApiServerException, ApiNotFoundException, ApiProcessingException {
		if (mail == null || mail.isEmpty()) {
			throw new ApiRequestException("Mail no puede estar vacío.");
		}
		Usuario usuarioEncontrado;
		try {
			usuarioEncontrado = usuarioRepository.findByMail(mail.trim());
		} catch (Exception e) {
			throw new ApiNotFoundException(e.getMessage());
		}

		if (usuarioEncontrado == null) {
			throw new ApiNotFoundException("El usuario no fue encontrado.");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(usuarioEncontrado);
		} catch (JsonProcessingException e) {
			throw new ApiProcessingException(e.getMessage());
		}
	}

	public Usuario getByName(String nombre) {
		return usuarioRepository.findByNombre(nombre);
	}

	// Update operation
	public Usuario actualizar(Usuario usuario) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		Usuario user = usuarioRepository.findByMail(usuario.getMail().trim());
		if (user == null) {
			throw new ApiNotFoundException(
					"El usuario " + usuario.getMail() + " no fue encontrado, verifique su mail.");
		}

		// Validación campos vacíos
		if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
			throw new ApiRequestException("Nombre no ingresado.");
		}
		if (usuario.getApellido() == null || usuario.getNombre().isEmpty()) {
			throw new ApiRequestException("Apellido no ingresado.");
		}
		if (usuario.getEdad() == null || usuario.getNombre().isEmpty()) {
			throw new ApiRequestException("Edad no ingresada.");
		}
		if (usuario.getDni() == null || usuario.getNombre().isEmpty()) {
			throw new ApiRequestException("DNI no ingresado.");
		}

		// Trims y normalizaciones
		String nombreUsuarioNuevo = usuario.getNombre().replaceAll("\\s{2,}", " ").trim();
		String apellidoUsuarioNuevo = usuario.getApellido().replaceAll("\\s{2,}", " ").trim();
		String edadUsuarioNuevo = usuario.getEdad().replaceAll("\s+", "").trim();
		String dniUsuarioNuevo = usuario.getDni().replaceAll(".", "").replaceAll("\s+", "").trim();

		// Validaciones campos mal completados
		if (usuario.getNombre().length() < 2) {
			throw new ApiRequestException("Nombre debe ser mayor a un caracter.");
		}
		if (usuario.getApellido().length() < 2) {
			throw new ApiRequestException("Apellido debe ser mayor a un caracter.");
		}
		if (!usuario.getEdad().matches("[0-9]+")) {
			throw new ApiRequestException("Edad sólo debe contener números.");
		}
		if (!usuario.getDni().matches("[0-9]+")) {
			throw new ApiRequestException("DNI sólo debe contener números.");
		}
		Usuario usuarioActualizado;
		try {
			user.setNombre(nombreUsuarioNuevo);
			user.setApellido(apellidoUsuarioNuevo);
			user.setEdad(edadUsuarioNuevo);
			user.setDni(dniUsuarioNuevo);
			usuarioActualizado = usuarioRepository.save(user);
		} catch (Exception e) {
			throw new ApiServerException(e.getMessage());
		}

		return usuarioActualizado;
	}

	// Delete operation
	public void borrarTodos() {
		usuarioRepository.deleteAll();
	}

	// Eliminar usuario
	public void eliminar(String mail) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		Usuario user = usuarioRepository.findByMail(mail.trim());
		if (user == null) {
			throw new ApiNotFoundException("El usuario " + mail + " no fue encontrado, verifique su mail.");
		}
		try {
			usuarioRepository.delete(user);
		} catch (Exception e) {
			throw new ApiServerException(e.getMessage());
		}
	}
}