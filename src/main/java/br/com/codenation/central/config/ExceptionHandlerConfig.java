package br.com.codenation.central.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.codenation.central.entity.Erro;
import br.com.codenation.central.service.exception.LogNotFoundException;

@ControllerAdvice
public class ExceptionHandlerConfig {
	
	@ExceptionHandler(LogNotFoundException.class)
	public ResponseEntity<Erro> telefoneNaoEncontrado(LogNotFoundException ex) {
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Erro(ex.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Erro> telefoneDuplicado(Exception ex){
		
		return ResponseEntity.badRequest().body(new Erro(ex.getMessage()));
	}
	
}
