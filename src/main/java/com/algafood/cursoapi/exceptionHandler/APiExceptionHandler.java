package com.algafood.cursoapi.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.exception.NegocioException;

@ControllerAdvice
public class APiExceptionHandler {


	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?>tratarEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException e){
		Problema problema =Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
	}
	

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?>tratarNegocioException(
			NegocioException e){
		Problema problema =Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
	}
	

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?>tratarHttpMediaTypeNotSupportedException() {
		Problema problema =Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem("O tipo de mídia não é aceito nesta api.").build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(problema);
	}
}
