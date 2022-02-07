package com.algafood.cursoapi.domain.repository;


/* link para documentação das Query
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
 */

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algafood.cursoapi.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {
	
	//@Query(value = "select c from Cozinha c where c.id = ?1")
	//List<Cozinha> buscarPorId(Long cozinhaId);
	
	@Query(value = "select c from Cozinha c where upper (trim(c.nome)) like %?1%")
	List<Cozinha> findByNomeContaining(String nome);
}

