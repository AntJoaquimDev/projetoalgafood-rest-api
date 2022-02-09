package com.algafood.cursoapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class Endereco {

	
	@Column(name ="endereco_cep")
	private String cep;
	@Column(name ="endereco_logradouro")
	private String logradouro;
	@Column(name ="endereco_numero")
	private String numero;
	@Column(name ="endereco_complemento")
	private String complemento;
	@Column(name ="endereco_bairro")
	private String bairro; 
	
	@ManyToOne(fetch = FetchType.LAZY)//mudando pradao EAGER p/lazy
	@JoinColumn(name= "endereco_cidade_id")
	private Cidade cidade;
	
	
}
