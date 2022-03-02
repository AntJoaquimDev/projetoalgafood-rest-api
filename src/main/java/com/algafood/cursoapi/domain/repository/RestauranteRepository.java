package com.algafood.cursoapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algafood.cursoapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository <Restaurante, Long>,RestauranteRepositoryQuerys ,
				 JpaSpecificationExecutor<Restaurante> {

	//@Query("from Restaurante r join fetch  r.cozinha join fetch r.formaPagamento")
	List<Restaurante>findAll();	
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial,BigDecimal taxaFinal);
	
	
	
}
