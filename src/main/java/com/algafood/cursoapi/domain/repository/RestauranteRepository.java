package com.algafood.cursoapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algafood.cursoapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository <Restaurante, Long>,RestauranteRepositoryQuerys ,
				 JpaSpecificationExecutor<Restaurante> {

	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial,BigDecimal taxaFinal);
	
	
	
}
