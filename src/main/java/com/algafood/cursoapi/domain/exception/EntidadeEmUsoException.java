package com.algafood.cursoapi.domain.exception;

public class EntidadeEmUsoException extends NegocioException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String messagem ) {
		super(messagem);
	}
}
