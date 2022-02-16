package com.algafood.cursoapi.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;	
	
	public CidadeNaoEncontradaException(String messagem) {
		super(messagem);
		
	}
	public CidadeNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de Cidade com o código %d",estadoId));
	}
}
