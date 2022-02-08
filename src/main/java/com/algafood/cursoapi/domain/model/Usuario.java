package com.algafood.cursoapi.domain.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)	
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;
	
	@ManyToOne
	private Permissao permissao;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@ManyToMany
	@JoinTable(name= "usuario_grupo",
	joinColumns = @JoinColumn(name= "usuario_id"),
	inverseJoinColumns = @JoinColumn(name= "grupo_id"))
	private List<Grupo> Grupos = new ArrayList<>();
}
