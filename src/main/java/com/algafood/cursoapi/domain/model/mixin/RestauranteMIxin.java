package com.algafood.cursoapi.domain.model.mixin;

import java.time.LocalDateTime;
import java.util.List;

import com.algafood.cursoapi.domain.model.Cozinha;
import com.algafood.cursoapi.domain.model.Endereco;
import com.algafood.cursoapi.domain.model.FormaPagamento;
import com.algafood.cursoapi.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class RestauranteMIxin {

	@JsonIgnoreProperties(value ="nome", allowGetters = true)	
	private Cozinha cozinha;
	
    @JsonIgnore	
	private List<FormaPagamento> formaPagamento ;		
	
	@JsonIgnore	
	private Endereco endereco;
	
	@JsonIgnore
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	
		
	@JsonIgnore
	private List<Produto> produtos ;	
	
}
