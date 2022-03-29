package com.algafood.cursoapi.model.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RestauranteInput {

	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	
	private CozinhaIdInput cozinha;
	
}
