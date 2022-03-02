package com.algafood.cursoapi;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Cozinha;
import com.algafood.cursoapi.domain.service.CadastroCozinhaService;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	public CadastroCozinhaService cadastroCozinhaService;
	private EntidadeNaoEncontradaException erroEsperado;

	@Test
	public void deveAtribuir_Id_QuandoCadastroCozinhaComSucesso() {
		// cenário hepp path

		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		// ação

		cadastroCozinhaService.salvar(novaCozinha);

		// validação

		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}

	
	@Test
	public void deveFalhar_QuandoCadastroCozinhaSemNome() {
		// cenário hepp path
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		// ação
		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			cadastroCozinhaService.salvar(novaCozinha);
		});
		// validação
		assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		var erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class,()->{
			cadastroCozinhaService.excluir(1L);
		});
		assertThat(erroEsperado);
	}
	@Test
	  public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		
	var	erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class, ()->{
			
			cadastroCozinhaService.excluir(100l);			
		});
	assertThat(erroEsperado);
	}
}
