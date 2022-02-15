package com.algafood.cursoapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algafood.cursoapi.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
	
	
}
