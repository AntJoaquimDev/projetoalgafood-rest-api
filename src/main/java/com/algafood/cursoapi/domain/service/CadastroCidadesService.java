package com.algafood.cursoapi.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Cidade;
import com.algafood.cursoapi.domain.model.Estados;
import com.algafood.cursoapi.domain.repository.CidadeRepository;
import com.algafood.cursoapi.domain.repository.EstadosRepository;

@Service
public class CadastroCidadesService {

	@Autowired
	private EstadosRepository estadosRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Optional<Estados> estado = estadosRepository.findById(estadoId);
		if (!estado.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de Estado com o código %d ", estadoId));
		}
		cidade.setEstado(estado.get());
		return cidadeRepository.saveAndFlush(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", cidadeId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
		}
	}
}
