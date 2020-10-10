package com.scoringsalud.app.puntuable.infrastructure;

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

import com.scoringsalud.app.puntuable.application.PuntuableService;

import com.scoringsalud.app.exceptions.application.ApiNotFoundException;
import com.scoringsalud.app.exceptions.application.ApiProcessingException;
import com.scoringsalud.app.exceptions.application.ApiRequestException;
import com.scoringsalud.app.exceptions.application.ApiServerException;
import com.scoringsalud.app.puntuable.domain.Puntuable;

@RestController
public class PuntuableController {

	@Autowired
	private PuntuableService puntuable;

	@PostMapping(path = "/puntuable/crearPuntuable")
	public ResponseEntity<String> crearPuntuable(@RequestBody Puntuable puntuable) {
		String puntuableCreado = this.puntuable.crear(puntuable).toString();
		return new ResponseEntity<>(puntuableCreado, HttpStatus.OK);
	}

	@PutMapping(path = "/puntuable/actualizarPuntuable")
	public ResponseEntity<String> actualizarPuntuable(@RequestBody Puntuable puntuable) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		String codigoPuntuableActualizado = this.puntuable.actualizar(puntuable).getCodigo();
		return new ResponseEntity<>("Puntuable  " + codigoPuntuableActualizado + " se actualizo correctamente.",
				HttpStatus.OK);
	}

	@PutMapping(path = "/puntuable/actualizarPuntuableActividad")
	public ResponseEntity<String> actualizarPuntuableActividad(@RequestBody Puntuable puntuable) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		String codigoPuntuableActualizado = this.puntuable.actualizarActividad(puntuable).getCodigo();
		return new ResponseEntity<>("Puntuable  " + codigoPuntuableActualizado + " se actualizo correctamente.",
				HttpStatus.OK);
	}
	
	/*
	@PutMapping(path = "/puntuable/actualizarPuntuableEstiramiento")
	public ResponseEntity<String> actualizarPuntuableEstiramiento(@RequestBody Puntuable puntuable) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		String codigoPuntuableActualizado = this.puntuable.actualizarEstiramiento(puntuable).getByCodigo(); 
		return new ResponseEntity<>("Puntuable  " + codigoPuntuableActualizado + " se actualizo correctamente.",
				HttpStatus.OK);
	}
	*/
	
	
	@GetMapping(path = "/puntuable/obtenerPuntuable", produces = "application/json")
	public ResponseEntity<String> buscarPuntuable(@RequestParam String codigo) throws ApiRequestException, ApiServerException, ApiNotFoundException, ApiProcessingException {
		String puntuableEncontrado = this.puntuable.getByCodigo(codigo);
		return new ResponseEntity<>(puntuableEncontrado, HttpStatus.OK);
	}

	@DeleteMapping(path = "/puntuable/eliminar")
	public ResponseEntity<String> delete(@RequestParam String codigo) throws ApiRequestException, ApiServerException, ApiNotFoundException {
		puntuable.eliminar(codigo);
		String successMessage = "Puntuable: " + codigo + " eliminado.";
		return new ResponseEntity<>(successMessage, HttpStatus.OK);
	}

}
