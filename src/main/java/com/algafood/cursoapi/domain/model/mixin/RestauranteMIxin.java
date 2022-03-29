package com.algafood.cursoapi.domain.model.mixin;

import java.time.OffsetDateTime;
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
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
		
	@JsonIgnore
	private List<Produto> produtos ;	
	
}
