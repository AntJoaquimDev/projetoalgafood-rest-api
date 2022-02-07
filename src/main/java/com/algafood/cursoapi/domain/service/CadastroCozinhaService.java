package com.algafood.cursoapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Cozinha;
import com.algafood.cursoapi.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

		
	public void excluir(Long cozinhaId) {
		try {			
				cozinhaRepository.deleteById(cozinhaId);			
			
		} catch (EmptyResultDataAccessException e) {			
			throw new EntidadeNaoEncontradaException(					
					String.format("Não existe um cadastro de Cozinha com o código %d",cozinhaId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cozinha de Código %d não pode ser removida, pois està em uso", cozinhaId));
		}
	}
	
	public List<Cozinha> buscarPorNome(String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}
}
