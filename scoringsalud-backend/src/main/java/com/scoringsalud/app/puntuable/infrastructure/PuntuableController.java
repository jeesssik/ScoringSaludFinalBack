package com.scoringsalud.app.puntuable.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.scoringsalud.app.puntuable.application.PuntuableService;

@RestController
public class PuntuableController {

	@Autowired
	private PuntuableService puntuable;
	
}
