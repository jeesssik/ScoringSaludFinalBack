package com.scoringsalud.app.puntuable.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoringsalud.app.exceptions.application.ApiNotFoundException;
import com.scoringsalud.app.exceptions.application.ApiProcessingException;
import com.scoringsalud.app.exceptions.application.ApiRequestException;
import com.scoringsalud.app.exceptions.application.ApiServerException;
import com.scoringsalud.app.puntuable.domain.Medidor;
import com.scoringsalud.app.puntuable.domain.Puntuable;
import com.scoringsalud.app.puntuable.domain.PuntuableRepository;
import com.scoringsalud.app.puntuable.domain.Actividad;

@Service
public class PuntuableService {

	@Autowired
	private PuntuableRepository puntuableRepository;
	
	// Create operation
		public Puntuable crear(Puntuable puntuable) throws ApiRequestException, ApiServerException {
			if (puntuable.getCodigo() == null || puntuable.getCodigo().isEmpty()) {
				throw new ApiRequestException("El campo codigo no puede estar vacio para crear el puntuable");
			}
			Puntuable puntuableEncontrado = puntuableRepository.findByCodigo(puntuable.getCodigo().trim());
			if (!(puntuableEncontrado == null)) {
				return puntuableEncontrado;
			}
			try {
				puntuableRepository.save(puntuable);
			} catch (Exception e) {
				throw new ApiServerException("Error al intentar crear el usuario");
			}
			return puntuableRepository.save(puntuable);
		}
	
		// Retrieve operation
		public List<Puntuable> getAll() {
			return puntuableRepository.findAll();
		}
		
		
	public String getByCodigo(String codigo)
			throws ApiRequestException, ApiServerException, ApiNotFoundException, ApiProcessingException {
		if (codigo == null || codigo.isEmpty()) {
			throw new ApiRequestException("El codigo no puede estar vacío.");
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
	
	// Update operation
		public Puntuable actualizar(Puntuable puntuable) throws ApiRequestException, ApiServerException, ApiNotFoundException {
			Puntuable p = puntuableRepository.findByCodigo(puntuable.getCodigo().trim());
			
			if (p == null) {
				throw new ApiNotFoundException(
						"El puntuable \" + puntuable.getCodigo() + \" no fue encontrado, verifique su codigo.");
			}

			// Validación campos vacíos
			if (puntuable.getNombre() == null || puntuable.getNombre().isEmpty()) {
				throw new ApiRequestException("Nombre no ingresado.");
			}
			
			if ((Integer)puntuable.getPuntosOtorgables() == null || (Integer)puntuable.getPuntosOtorgables() == 0) {
				throw new ApiRequestException("Puntos otorgables no ingresado.");
			}
			
			// Trims y normalizaciones
			String nombrePuntuableNuevo = puntuable.getNombre().replaceAll("\\s{2,}", " ").trim();
			Integer puntosPuntuableNuevo = Integer.valueOf(puntuable.getPuntosOtorgables());


			// Validaciones campos mal completados
			if (puntuable.getNombre().length() < 2) {
				throw new ApiRequestException("Nombre debe ser mayor a un caracter.");
			}
			if (puntuable.getPuntosOtorgables() > 0) {
				throw new ApiRequestException("Los puntos no pueden ser menor a 0.");
			}
			
			Puntuable puntuableActualizado;
			try {
				p.setNombre(nombrePuntuableNuevo);
				p.setPuntosOtorgables(puntosPuntuableNuevo);
				puntuableActualizado = puntuableRepository.save(p);
			} catch (Exception e) {
				throw new ApiServerException(e.getMessage());
			}
			
			return puntuableActualizado;
		}
		
		public Puntuable actualizarActividad(Puntuable puntuable) throws ApiRequestException, ApiServerException, ApiNotFoundException {
			Puntuable p = puntuableRepository.findByCodigo(puntuable.getCodigo().trim());
			
			if (p == null) {
				throw new ApiNotFoundException(
						"El puntuable " + puntuable.getCodigo() + " no fue encontrado, verifique su codigo.");
			}
			if(!(p instanceof Actividad)) {
				throw new ApiNotFoundException(
						"El puntuable " + puntuable.getCodigo() + " no es una Actividad.");
			}
			
			try {
				p = actualizar(p);
			}catch(Exception e) {
				throw new ApiServerException(e.getMessage());
			}
			
			// Validación campos vacíos
			if (Boolean.valueOf(((Actividad)puntuable).isPosicionUnica()) == null) {
				throw new ApiRequestException("Posicion Unica no ingresado.");
			}
			
			if (Integer.valueOf(((Actividad)puntuable).getRepeticiones()) == null) {
				throw new ApiRequestException("Repeticiones no ingresado.");
			}
			
			// Trims y normalizaciones
			Boolean posicionPuntuableNuevo = ((Actividad)puntuable).isPosicionUnica();
			Integer repeticionesPuntuableNuevo = Integer.valueOf(((Actividad)puntuable).getRepeticiones());
			ArrayList<Medidor> medidoresPuntuableNuevo = ((Actividad)puntuable).getMedidores();

			// Validaciones campos mal completados
			if (Integer.valueOf(((Actividad)puntuable).getRepeticiones()) <=0) {
				throw new ApiRequestException("Las Repeticiones deben ser mayores a 0.");
			}
			
			Puntuable puntuableActualizado;
			try {
				((Actividad)p).setPosicionUnica(posicionPuntuableNuevo);
				((Actividad)p).setRepeticiones(repeticionesPuntuableNuevo);
				((Actividad)p).setMedidores(medidoresPuntuableNuevo);
				puntuableActualizado = puntuableRepository.save(p);
			} catch (Exception e) {
				throw new ApiServerException(e.getMessage());
			}
			
			return puntuableActualizado;
		}
		
		public void borrarTodos() {
			puntuableRepository.deleteAll();
		}

		// Eliminar usuario
		public void eliminar(String codigo) throws ApiRequestException, ApiServerException, ApiNotFoundException {
			Puntuable puntuable = puntuableRepository.findByCodigo(codigo.trim());
			if (puntuable == null) {
				throw new ApiNotFoundException("El puntuable con codigo" + codigo + " no fue encontrado, verifique su puntuable.");
			}
			try {
				puntuableRepository.delete(puntuable);
			} catch (Exception e) {
				throw new ApiServerException(e.getMessage());
			}
		}
	

}
