package com.algafood.cursoapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String cep;
	@Column
	private String logradouro;
	@Column
	private String numero;
	@Column
	private String complemento;
	@Column
	private String bairro; 
	@ManyToOne
	private Cidade cidade;
	
	
}
