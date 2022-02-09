package com.algafood.cursoapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurante")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)//mudando pradao EAGER p/lazy
	@JoinColumn(name="cozinha_id")
	private Cozinha cozinha;
	
	//@JsonIgnore
	@ManyToMany //(fetch = FetchType.EAGER) //mudando pradao LAZY p/Eager nao é muito comun é perigoso
	@JoinTable(name= "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name= "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name= "forma_pagamento_id"))
	private List<FormaPagamento> formaPagamento = new  ArrayList<>() ;
	
	
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false,columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;
	
		
	@JsonIgnore
	@OneToMany
	private List<Produto> produtos = new ArrayList<>();	
	
	@ManyToOne(fetch = FetchType.LAZY)//mudando pradao EAGER p/lazy
	private Usuario usuario;
}
