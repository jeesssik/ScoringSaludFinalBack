package com.scoringsalud.app.application.puntuable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoringsalud.app.application.exceptions.ApiNotFoundException;
import com.scoringsalud.app.application.exceptions.ApiProcessingException;
import com.scoringsalud.app.application.exceptions.ApiRequestException;
import com.scoringsalud.app.application.exceptions.ApiServerException;
import com.scoringsalud.app.domain.puntuable.Puntuable;
import com.scoringsalud.app.domain.puntuable.PuntuableRepository;


public class PuntuableService {

	@Autowired
	private PuntuableRepository puntuableRepository;
	
	public String getByNombre(String nombre)
			throws ApiRequestException, ApiServerException, ApiNotFoundException, ApiProcessingException {
		if (nombre == null || nombre.isEmpty()) {
			throw new ApiRequestException("El nombre no puede estar vacío.");
		}
		Puntuable puntuableEncontrado;
		try {
			puntuableEncontrado = puntuableRepository.findByNombre(nombre.trim());
		} catch (Exception e) {
			throw new ApiNotFoundException(e.getMessage());
		}

		if (puntuableEncontrado == null) {
			throw new ApiNotFoundException("El puntuable no fue encontrado.");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(puntuableEncontrado);
		} catch (JsonProcessingException e) {
			throw new ApiProcessingException(e.getMessage());
		}
	}

}
