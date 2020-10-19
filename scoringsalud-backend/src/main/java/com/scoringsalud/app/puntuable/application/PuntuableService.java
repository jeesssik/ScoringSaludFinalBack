package com.scoringsalud.app.puntuable.application;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoringsalud.app.exceptions.application.ApiNotFoundException;
import com.scoringsalud.app.exceptions.application.ApiProcessingException;
import com.scoringsalud.app.exceptions.application.ApiRequestException;
import com.scoringsalud.app.exceptions.application.ApiServerException;
import com.scoringsalud.app.puntuable.domain.Puntuable;
import com.scoringsalud.app.puntuable.domain.PuntuableRepository;


public class PuntuableService {

	@Autowired
	private PuntuableRepository puntuableRepository;
	
	
	public String getByCodigo(String codigo)
			throws ApiRequestException, ApiServerException, ApiNotFoundException, ApiProcessingException {
		if (codigo == null || codigo.isEmpty()) {
			throw new ApiRequestException("El nombre no puede estar vacío.");
		}
		Puntuable puntuableEncontrado;
		try {
			puntuableEncontrado = puntuableRepository.findByCodigo(codigo.trim());
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
