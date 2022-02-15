package com.algafood.cursoapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Estado;
import com.algafood.cursoapi.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_NÃO_PODE_SER_REMOVIDA_EM_USO 
	= "Estado de Código %d não pode ser removida, pois està em uso";
	
	private static final String MSG_ESTADO_NAO_ENCONTRADO 
	= "Não existe um cadastro de Estado com o código %d";
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {

		return estadoRepository.saveAndFlush(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_NÃO_PODE_SER_REMOVIDA_EM_USO, estadoId));
		}
	}
	
	public Estado burcarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO,estadoId)));
	}
}
