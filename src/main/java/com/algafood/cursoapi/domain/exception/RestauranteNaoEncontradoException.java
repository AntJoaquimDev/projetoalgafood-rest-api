package com.algafood.cursoapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;	
	
	public RestauranteNaoEncontradoException(String messagem) {
		super(messagem);
		
	}
	public RestauranteNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de Estado com o código %d",estadoId));
	}
}
