package com.algafood.cursoapi.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;	
	
	public CozinhaNaoEncontradaException(String messagem) {
		super(messagem);
		
	}
	public CozinhaNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de Cozinha com o código %d",estadoId));
	}
}
