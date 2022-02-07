package com.algafood.cursoapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algafood.cursoapi.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository <Cidade, Long> {

}
