package com.scoringsalud.app.puntuable.infrastructure;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scoringsalud.app.puntuable.application.PuntuableService;

import com.scoringsalud.app.exceptions.application.ApiRequestException;
import com.scoringsalud.app.exceptions.application.ApiServerException;
import com.scoringsalud.app.puntuable.domain.Puntuable;
import com.scoringsalud.app.reporte.domain.Reporte;

@RestController
public class PuntuableController {

	@Autowired
	private PuntuableService puntuable;

	@PostMapping(path = "/puntuable/crearPuntuable",headers = "Accept=application/json", consumes="application/json")
	public ResponseEntity<String> crearPuntuable(@RequestBody Puntuable puntuable) {
		String puntuableCreado = this.puntuable.crearPuntuable(puntuable).toString();
		return new ResponseEntity<>(puntuableCreado, HttpStatus.OK);
	}
	
	@PutMapping(path = "/puntuable/actualizarPuntuable")
	public ResponseEntity<Integer> actualizarPuntuable( @RequestParam(name = "mail") String mail, @RequestParam(name = "tipo") String tipo, @RequestParam(name = "unidades") Integer unidades) throws ApiRequestException, ApiServerException, ParseException {
		Integer unidadesDia = this.puntuable.actualizarPuntuable(mail, tipo, unidades);
		return new ResponseEntity<>(unidadesDia, HttpStatus.OK);
	}
	
	@GetMapping(path = "/puntuable/obtenerPuntosDia" )
	public ResponseEntity<Integer> obtenerPuntosDia(@RequestParam(name = "mail") String mail) throws ApiRequestException, ApiServerException, ParseException{
		Integer puntosDia = this.puntuable.obtenerPuntosDia(mail);
		return new ResponseEntity<>(puntosDia, HttpStatus.OK);
	}
	
	@GetMapping(path = "/puntuable/obtenerReportes" )
	public ResponseEntity<ArrayList<Reporte>> obtenerReportes(@RequestParam(name = "mail") String mail, @RequestParam(name = "tipo") String tipo) throws ApiRequestException, ApiServerException, ParseException{
		ArrayList<Reporte> reportes = this.puntuable.obtenerReportes(mail, tipo);
		return new ResponseEntity<>(reportes, HttpStatus.OK);
	}
	

}

/*
 * @PutMapping(path = "/puntuable/actualizarAgua") public
 * ResponseEntity<Integer> actualizarAgua( @RequestParam(name = "mail") String
 * mail, @RequestParam(name = "unidades") Integer vasos) throws
 * ApiRequestException, ApiServerException, ParseException { Integer vasosDia =
 * this.puntuable.actualizarAgua(mail, vasos); return new
 * ResponseEntity<>(vasosDia, HttpStatus.OK); }
 */
