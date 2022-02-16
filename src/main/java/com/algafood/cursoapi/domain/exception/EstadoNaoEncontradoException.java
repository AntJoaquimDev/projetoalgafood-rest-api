package com.algafood.cursoapi.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;	
	
	public EstadoNaoEncontradoException(String messagem) {
		super(messagem);
		
	}
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de Estado com o código %d",estadoId));
	}
}
