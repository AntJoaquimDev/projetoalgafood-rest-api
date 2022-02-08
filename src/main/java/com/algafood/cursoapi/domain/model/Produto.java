package com.algafood.cursoapi.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nome_produto")
	private String nome;
	@Column(name = "descricao_produto")
	private String descricao;
	
	@Column(name = "preco_produto")
	private BigDecimal preco;
	
	@Column(name = "ativo_produto")
	private Boolean ativo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
}
