package com.scoringsalud.app.puntuable.domain;


import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface PuntuableRepository extends MongoRepository<Puntuable, String> { // donde dice String es el tipo de la PK (wrapper, porque debe ser un Object)
	
	
	@Query("{ $and: [ { fecha: {$gte: ?0} }, { userMail: { $eq: ?1} } ] }")
	List<Puntuable> findByFechaGreaterThanAndUserMail(Instant date, String userMail);
	
	@Query("{ $and: [ { fecha: {$gte: ?0} }, { fecha: { $lte: ?1} } { userMail: { $eq: ?2} } ] }")
	List<Puntuable> findByFechaBetweenAndUserMail(Instant fromDate, Instant toDate, String userMail);
	
	@Query("{ $and: [ { fecha: {$gte: ?0} }, { userMail: { $eq: ?1} } { tipo: { $eq: ?2} } ] }")
	List<Puntuable> findByFechaUserMailAndTipo(Instant date, String userMail, String tipo);
}