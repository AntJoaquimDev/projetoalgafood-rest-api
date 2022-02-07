package com.algafood.cursoapi.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String messagem) {
		super(messagem);
		
	}
}
