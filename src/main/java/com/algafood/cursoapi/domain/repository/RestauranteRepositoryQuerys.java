package com.algafood.cursoapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algafood.cursoapi.domain.model.Restaurante;

public interface RestauranteRepositoryQuerys {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

	List<Restaurante> findComFreteGratis(String nome);
		
	
}