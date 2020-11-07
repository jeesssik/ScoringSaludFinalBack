package com.scoringsalud.app.puntuable.application;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scoringsalud.app.exceptions.application.ApiRequestException;
import com.scoringsalud.app.exceptions.application.ApiServerException;
import com.scoringsalud.app.puntuable.domain.Puntuable;
import com.scoringsalud.app.puntuable.domain.PuntuableRepository;
import com.scoringsalud.app.reporte.domain.Reporte;
import com.scoringsalud.app.user.domain.Usuario;
import com.scoringsalud.app.user.domain.UsuarioRepository;

@Service
public class PuntuableService {

	@Autowired
	private PuntuableRepository puntuableRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// CREAR PUNTUABLE
	public Puntuable crearPuntuable(Puntuable puntuable) throws ApiRequestException, ApiServerException {
		puntuableRepository.save(puntuable);
		String mail = puntuable.getUserMail();
		Usuario usuarioEncontrado = usuarioRepository.findByMail(mail);
		usuarioEncontrado.addPuntuable(puntuable);
		usuarioRepository.save(usuarioEncontrado);
		return puntuable;
	}

	// OBTENER PUNTOS DEL DIA
	public Integer obtenerPuntosDia(String mail) throws ApiRequestException, ApiServerException, ParseException {
		int puntosTotales = 0;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		Instant instant = todayWithZeroTime.toInstant();
		List<Puntuable> puntuablesEncontrados = puntuableRepository.findByFechaGreaterThanAndUserMail(instant, mail);
		ArrayList<Puntuable> puntuablesDia = new ArrayList<Puntuable>(puntuablesEncontrados);
		for (Puntuable puntuable : puntuablesDia) {
			int puntosActividad = puntuable.getPuntos();
			puntosTotales = puntosTotales + puntosActividad;
		}
		return puntosTotales;
	}

	
	// ACTUALIZAR PUNTUABLE DEL DIA
	public Integer actualizarPuntuable(String mail, String tipo, Integer unidades) throws ApiRequestException, ApiServerException, ParseException {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			Date todayWithZeroTime = formatter.parse(formatter.format(today));
			Instant instant = todayWithZeroTime.toInstant();
			List<Puntuable> puntuableEncontrado = puntuableRepository.findByFechaUserMailAndTipo(instant, mail,tipo);
			Puntuable puntuableActualizado;
			int unidadesActualizadas = 0;
			if(tipo.equals("Agua")) {
				ArrayList<Puntuable> puntuablesAgua = new ArrayList<Puntuable>(puntuableEncontrado);
				if( puntuablesAgua.isEmpty()) {
					puntuableActualizado = new Puntuable("Agua", "Vasos Agua", 0, "", mail);
				}else {
					puntuableActualizado = puntuablesAgua.get(0);
					puntuableActualizado.setUnidades(unidades);
					unidadesActualizadas = unidades;
				}
				int puntosOtorgados;
				if(unidades > 8) {
					puntosOtorgados = 100;
				}else {
					puntosOtorgados = unidades*10;
				}
				puntuableActualizado.setPuntos(puntosOtorgados);
				puntuableRepository.save(puntuableActualizado);
			}else if(tipo.equals("Sueño")) {
				ArrayList<Puntuable> puntuablesSueño = new ArrayList<Puntuable>(puntuableEncontrado);
				if( puntuablesSueño.isEmpty()) {
					puntuableActualizado = new Puntuable("Sueño", "Horas de Sueño", 0, "", mail);
				}else {
					puntuableActualizado = puntuablesSueño.get(0);
					puntuableActualizado.setUnidades(unidades);
					unidadesActualizadas = unidades;
				}
				int puntosOtorgados;
				if(unidades > 8) {
					puntosOtorgados = 100;
				}else if(unidades < 6){
					puntosOtorgados = 0;
				}else {
					puntosOtorgados = unidades*10;
				}
				puntuableActualizado.setPuntos(puntosOtorgados);
				puntuableRepository.save(puntuableActualizado);
			}else if(tipo.equals("Pasos")) {
				ArrayList<Puntuable> puntuablesPasos = new ArrayList<Puntuable>(puntuableEncontrado);
				if( puntuablesPasos.isEmpty()) {
					puntuableActualizado = new Puntuable("Pasos", "Pasos Realizados", 0, "", mail);
				}else {
					puntuableActualizado = puntuablesPasos.get(0);
					puntuableActualizado.setUnidades(unidades);
					unidadesActualizadas = unidades;
				}
				int puntosOtorgados = unidades+1/10;
				puntuableActualizado.setPuntos(puntosOtorgados);
				puntuableRepository.save(puntuableActualizado);
			}
			
			return unidadesActualizadas;
		}

	
	// OBTENER REPORTES
	public ArrayList<Reporte> obtenerReportes(String mail, String tipo) throws ApiRequestException, ApiServerException, ParseException {
		ArrayList<Reporte> listaDeReportes = new ArrayList<Reporte>();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if(tipo.equals("semana")) {
			DateTimeFormatter fromInstantToLocalFormatter = DateTimeFormatter.ofPattern("EEE dd").withZone(ZoneId.of("America/Argentina/Buenos_Aires"));
			Date today = new Date();
			Date todayWithZeroTime = formatter.parse(formatter.format(today));
			Instant hoy = todayWithZeroTime.toInstant();
			Instant ayer = hoy.minus(1, ChronoUnit.DAYS);
			Instant dosDiasAntes = hoy.minus(2, ChronoUnit.DAYS);
			Instant tresDiasAntes = hoy.minus(3, ChronoUnit.DAYS);
			Instant cuatroDiasAntes = hoy.minus(4, ChronoUnit.DAYS);
			Instant cincoDiasAntes = hoy.minus(5, ChronoUnit.DAYS);
			Instant seisDiasAntes = hoy.minus(6, ChronoUnit.DAYS);
			List<Puntuable> puntuablesHoy = puntuableRepository.findByFechaGreaterThanAndUserMail(hoy, mail);
			List<Puntuable> puntuablesAyer = puntuableRepository.findByFechaBetweenAndUserMail(ayer, hoy, mail);
			List<Puntuable> puntuablesDosDiasAntes = puntuableRepository.findByFechaBetweenAndUserMail(dosDiasAntes, ayer, mail);
			List<Puntuable> puntuablesTresDiasAntes = puntuableRepository.findByFechaBetweenAndUserMail(tresDiasAntes, dosDiasAntes, mail);
			List<Puntuable> puntuablesCuatroDiasAntes = puntuableRepository.findByFechaBetweenAndUserMail(cuatroDiasAntes, tresDiasAntes, mail);
			List<Puntuable> puntuablesCincoDiasAntes = puntuableRepository.findByFechaBetweenAndUserMail(cincoDiasAntes, cuatroDiasAntes, mail);
			List<Puntuable> puntuablesSeisDiasAntes = puntuableRepository.findByFechaBetweenAndUserMail(seisDiasAntes, cincoDiasAntes, mail);
			
			//HOY
			String fechaHoy = fromInstantToLocalFormatter.format( hoy );
			ArrayList<Puntuable> puntuablesHoyParaIterar = new ArrayList<Puntuable>(puntuablesHoy);
			Reporte reporteHoy = listaPuntuablesToReporte(puntuablesHoyParaIterar, fechaHoy);
			listaDeReportes.add(reporteHoy);
			
			//AYER
			String fechaAyer = fromInstantToLocalFormatter.format( ayer );
			ArrayList<Puntuable>  puntuablesAyerParaIterar = new ArrayList<Puntuable>( puntuablesAyer);
			Reporte reporteAyer = listaPuntuablesToReporte(puntuablesAyerParaIterar, fechaAyer);
			listaDeReportes.add(reporteAyer);
			
			//DOS DIAS ANTES
			String fechadosDiasAntes = fromInstantToLocalFormatter.format( dosDiasAntes );
			ArrayList<Puntuable> puntuablesDosDiasAntesParaIterar = new ArrayList<Puntuable>(puntuablesDosDiasAntes);
			Reporte reporteDosDiasAntes = listaPuntuablesToReporte(puntuablesDosDiasAntesParaIterar, fechadosDiasAntes);
			listaDeReportes.add(reporteDosDiasAntes);
			
			//TRES DIAS ANTES
			String fechaTresDiasAntes = fromInstantToLocalFormatter.format( tresDiasAntes );
			ArrayList<Puntuable> puntuablesTresDiasAntesParaIterar = new ArrayList<Puntuable>(puntuablesTresDiasAntes);
			Reporte reporteTresDiasAntes = listaPuntuablesToReporte(puntuablesTresDiasAntesParaIterar, fechaTresDiasAntes);
			listaDeReportes.add(reporteTresDiasAntes);
			
			//CUATRO DIAS ANTES
			String fechaCuatroDiasAntes = fromInstantToLocalFormatter.format( cuatroDiasAntes );
			ArrayList<Puntuable> puntuablesCuatroDiasAntesParaIterar = new ArrayList<Puntuable>(puntuablesCuatroDiasAntes);
			Reporte reporteCuatroDiasAntes = listaPuntuablesToReporte(puntuablesCuatroDiasAntesParaIterar, fechaCuatroDiasAntes);
			listaDeReportes.add(reporteCuatroDiasAntes);
			
			//CINCO DIAS ANTES
			String fechaCincoDiasAntes = fromInstantToLocalFormatter.format( cincoDiasAntes );
			ArrayList<Puntuable> puntuablesCincoDiasAntesParaIterar = new ArrayList<Puntuable>(puntuablesCincoDiasAntes);
			Reporte reporteCincoDiasAntes = listaPuntuablesToReporte(puntuablesCincoDiasAntesParaIterar, fechaCincoDiasAntes);
			listaDeReportes.add(reporteCincoDiasAntes);
			
			//SEIS DIAS ANTES
			String fechaSeisDiasAntes = fromInstantToLocalFormatter.format( seisDiasAntes );
			ArrayList<Puntuable> puntuablesSeisDiasAntesParaIterar = new ArrayList<Puntuable>(puntuablesSeisDiasAntes);
			Reporte reporteSeisDiasAntes = listaPuntuablesToReporte(puntuablesSeisDiasAntesParaIterar, fechaSeisDiasAntes);
			listaDeReportes.add(reporteSeisDiasAntes);
			
		}else if(tipo.equals("año")) {
			int año = Calendar.getInstance().get(Calendar.YEAR);
			LocalDateTime eneroInicio = LocalDateTime.of(año, 1, 1, 0, 0);
			LocalDateTime febreroInicio = LocalDateTime.of(año, 2, 1, 0, 0);
			LocalDateTime marzoInicio = LocalDateTime.of(año, 3, 1, 0, 0);
			LocalDateTime abrilInicio = LocalDateTime.of(año, 4, 1, 0, 0);
			LocalDateTime mayoInicio = LocalDateTime.of(año, 5, 1, 0, 0);
			LocalDateTime junioInicio = LocalDateTime.of(año, 6, 1, 0, 0);
			LocalDateTime julioInicio = LocalDateTime.of(año, 7, 1, 0, 0);
			LocalDateTime agostoInicio = LocalDateTime.of(año, 8, 1, 0, 0);
			LocalDateTime septiembreInicio = LocalDateTime.of(año, 9, 1, 0, 0);
			LocalDateTime octubreInicio = LocalDateTime.of(año, 10, 1, 0, 0);
			LocalDateTime noviembreInicio = LocalDateTime.of(año, 11, 1, 0, 0);
			LocalDateTime diciembreInicio = LocalDateTime.of(año, 12, 1, 0, 0);
			LocalDateTime eneroSiguienteInicio = LocalDateTime.of(año+1, 1, 1, 0, 0);
			Instant enero = eneroInicio.toInstant(ZoneOffset.UTC);
			Instant febrero = febreroInicio.toInstant(ZoneOffset.UTC);
			Instant marzo = marzoInicio.toInstant(ZoneOffset.UTC);
			Instant abril = abrilInicio.toInstant(ZoneOffset.UTC);
			Instant mayo = mayoInicio.toInstant(ZoneOffset.UTC);
			Instant junio = junioInicio.toInstant(ZoneOffset.UTC);
			Instant julio = julioInicio.toInstant(ZoneOffset.UTC);
			Instant agosto = agostoInicio.toInstant(ZoneOffset.UTC);
			Instant septiembre = septiembreInicio.toInstant(ZoneOffset.UTC);
			Instant octubre = octubreInicio.toInstant(ZoneOffset.UTC);
			Instant noviembre = noviembreInicio.toInstant(ZoneOffset.UTC);
			Instant diciembre = diciembreInicio.toInstant(ZoneOffset.UTC);
			Instant eneroAñoSiguiente = eneroSiguienteInicio.toInstant(ZoneOffset.UTC);
			List<Puntuable> puntuablesEnero = puntuableRepository.findByFechaBetweenAndUserMail(enero, febrero, mail);
			List<Puntuable> puntuablesFebrero = puntuableRepository.findByFechaBetweenAndUserMail(febrero, marzo, mail);
			List<Puntuable> puntuablesMarzo = puntuableRepository.findByFechaBetweenAndUserMail(marzo, abril, mail);
			List<Puntuable> puntuablesAbril = puntuableRepository.findByFechaBetweenAndUserMail(abril, mayo, mail);
			List<Puntuable> puntuablesMayo = puntuableRepository.findByFechaBetweenAndUserMail(mayo, junio, mail);
			List<Puntuable> puntuablesJunio = puntuableRepository.findByFechaBetweenAndUserMail(junio, julio, mail);
			List<Puntuable> puntuablesJulio = puntuableRepository.findByFechaBetweenAndUserMail(julio, agosto, mail);
			List<Puntuable> puntuablesAgosto = puntuableRepository.findByFechaBetweenAndUserMail(agosto, septiembre, mail);
			List<Puntuable> puntuablesSeptiembre = puntuableRepository.findByFechaBetweenAndUserMail(septiembre, octubre, mail);
			List<Puntuable> puntuablesOctubre = puntuableRepository.findByFechaBetweenAndUserMail(octubre, noviembre, mail);
			List<Puntuable> puntuablesNoviembre = puntuableRepository.findByFechaBetweenAndUserMail(noviembre, diciembre, mail);
			List<Puntuable> puntuablesDiciembre = puntuableRepository.findByFechaBetweenAndUserMail(diciembre, eneroAñoSiguiente, mail);
			
			//ENERO
			ArrayList<Puntuable> puntuablesEneroParaIterar = new ArrayList<Puntuable>(puntuablesEnero);
			Reporte reporteEnero = listaPuntuablesToReporte(puntuablesEneroParaIterar, "Enero");
			//convertir reporte a promedios mensuales, le envío el reporte y la cantidad de días y me devuelve el reporte -> Mejor que el reporte tenga los totales
			listaDeReportes.add(reporteEnero);
			
			//FEBRERO
			ArrayList<Puntuable> puntuablesFebreroParaIterar = new ArrayList<Puntuable>(puntuablesFebrero);
			Reporte reporteFebrero = listaPuntuablesToReporte(puntuablesFebreroParaIterar, "Febrero");
			listaDeReportes.add(reporteFebrero);
			
			//MARZO
			ArrayList<Puntuable> puntuablesMarzoParaIterar = new ArrayList<Puntuable>(puntuablesMarzo);
			Reporte reporteMarzo = listaPuntuablesToReporte(puntuablesMarzoParaIterar, "Marzo");
			listaDeReportes.add(reporteMarzo);
			
			//ABRIL
			ArrayList<Puntuable> puntuablesAbrilParaIterar = new ArrayList<Puntuable>(puntuablesAbril);
			Reporte reporteAbril = listaPuntuablesToReporte(puntuablesAbrilParaIterar, "Abril");
			listaDeReportes.add(reporteAbril);
			
			//MAYO
			ArrayList<Puntuable> puntuablesMayoParaIterar = new ArrayList<Puntuable>(puntuablesMayo);
			Reporte reporteMayo = listaPuntuablesToReporte(puntuablesMayoParaIterar, "Mayo");
			listaDeReportes.add(reporteMayo);
			
			//JUNIO
			ArrayList<Puntuable> puntuablesJunioParaIterar = new ArrayList<Puntuable>(puntuablesJunio);
			Reporte reporteJunio = listaPuntuablesToReporte(puntuablesJunioParaIterar, "Junio");
			listaDeReportes.add(reporteJunio);
			
			//JULIO
			ArrayList<Puntuable> puntuablesJulioParaIterar = new ArrayList<Puntuable>(puntuablesJulio);
			Reporte reporteJulio = listaPuntuablesToReporte(puntuablesJulioParaIterar, "Julio");
			listaDeReportes.add(reporteJulio);
			
			//AGOSTO
			ArrayList<Puntuable> puntuablesAgostoParaIterar = new ArrayList<Puntuable>(puntuablesAgosto);
			Reporte reporteAgosto = listaPuntuablesToReporte(puntuablesAgostoParaIterar, "Agosto");
			listaDeReportes.add(reporteAgosto);
			
			//SEPTIEMBRE
			ArrayList<Puntuable> puntuablesSeptiembreParaIterar = new ArrayList<Puntuable>(puntuablesSeptiembre);
			Reporte reporteSeptiembre = listaPuntuablesToReporte(puntuablesSeptiembreParaIterar, "Septiembre");
			listaDeReportes.add(reporteSeptiembre);
			
			//OCTUBRE
			ArrayList<Puntuable> puntuablesOctubreParaIterar = new ArrayList<Puntuable>(puntuablesOctubre);
			Reporte reporteOctubre = listaPuntuablesToReporte(puntuablesOctubreParaIterar, "Octubre");
			listaDeReportes.add(reporteOctubre);
			
			//NOVIEMBRE
			ArrayList<Puntuable> puntuablesNoviembreParaIterar = new ArrayList<Puntuable>(puntuablesNoviembre);
			Reporte reporteNoviembre = listaPuntuablesToReporte(puntuablesNoviembreParaIterar, "Noviembre");
			listaDeReportes.add(reporteNoviembre);
			
			//DICIEMBRE
			ArrayList<Puntuable> puntuablesDiciembreParaIterar = new ArrayList<Puntuable>(puntuablesDiciembre);
			Reporte reporteDiciembre = listaPuntuablesToReporte(puntuablesDiciembreParaIterar, "Diciembre");
			listaDeReportes.add(reporteDiciembre);
		}
		
		
		
		return listaDeReportes;
	}


	private Reporte listaPuntuablesToReporte(ArrayList<Puntuable> puntuablesParaIterar, String title) {
		int puntos = 0;
		int cantEjercicios = 0;
		int horasDeSueño = 0;
		int vasosDeAgua = 0;
		int cantidadDePasos = 0;
		if(!puntuablesParaIterar.isEmpty()) {
			for (Puntuable puntuable : puntuablesParaIterar) {
				if(puntuable.getTipo().equals("Ejercicio")) {
					cantEjercicios++;
				}else if(puntuable.getTipo().equals("Sueño")) {
					horasDeSueño = puntuable.getUnidades();
				}else if(puntuable.getTipo().equals("Agua")) {
					vasosDeAgua = puntuable.getUnidades();
				}else if(puntuable.getTipo().equals("Pasos")) {
					cantidadDePasos = puntuable.getUnidades();
				}
				puntos = puntos + puntuable.getPuntos(); 
			}
		}
		Reporte reporte = new Reporte(title, puntos, cantEjercicios, horasDeSueño, vasosDeAgua, cantidadDePasos);
		return reporte;
	}
	
}

/*
 * // ACTUALIZAR VASOS DE AGUA DEL DIA public Integer actualizarAgua(String
 * mail, Integer vasos) throws ApiRequestException, ApiServerException,
 * ParseException { DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 * Date today = new Date(); Date todayWithZeroTime =
 * formatter.parse(formatter.format(today)); Instant instant =
 * todayWithZeroTime.toInstant(); List<Puntuable> aguaEncontrada =
 * puntuableRepository.findByFechaUserMailAndTipo(instant, mail,"Agua");
 * ArrayList<Puntuable> puntuablesAgua = new
 * ArrayList<Puntuable>(aguaEncontrada); Puntuable agua; if(
 * puntuablesAgua.isEmpty()) { agua = new Puntuable("Agua", "Vasos Agua", 0,
 * vasos, "", mail); }else { agua = puntuablesAgua.get(0);
 * agua.setUnidades(vasos); } int puntosOtorgados; if(vasos > 8) {
 * puntosOtorgados = 100; }else { puntosOtorgados = vasos*10; }
 * agua.setPuntos(puntosOtorgados); puntuableRepository.save(agua); return
 * agua.getUnidades(); }
 */