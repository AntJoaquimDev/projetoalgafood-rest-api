package com.algafood.cursoapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Estados;
import com.algafood.cursoapi.domain.repository.EstadosRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadosRepository estadosRepository;

	public Estados salvar(Estados estado) {

		return estadosRepository.saveAndFlush(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadosRepository.deleteById(estadoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de Estado com o código %d", estadoId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de Código %d não pode ser removida, pois està em uso", estadoId));
		}
	}
}
