package com.scoringsalud.app.domain.puntuable;

import java.time.LocalDateTime;

public class Postergada implements Estado {

	@Override
	public void proceso(Notificacion ctx) {
		LocalDateTime horaNueva = ctx.getHoraInicio(); // evitar bucle de hora , busca solucion 
		ctx.setHoraInicio(horaNueva); 
		//enviar mensaje de seteo, no hace mas nada 
	}

}
