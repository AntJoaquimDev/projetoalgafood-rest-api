package com.algafood.cursoapi.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.RestauranteNaoEncontradoException;
import com.algafood.cursoapi.domain.model.Cozinha;
import com.algafood.cursoapi.domain.model.Restaurante;
import com.algafood.cursoapi.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String RESTAURANTE_PODE_SER_REMOVIDA_EM_USO 
	= "Restaurante de Código %d não pode ser removida, pois està em uso";

	

	@Autowired
	private RestauranteRepository restauranteRepository;

	
	@Autowired
	CadastroCozinhaService cadastroCozinhaService;

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}
	@Transactional
	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);

		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException( restauranteId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(RESTAURANTE_PODE_SER_REMOVIDA_EM_USO, restauranteId));
		}
	}
	
	
	public List<Restaurante> buscarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new RestauranteNaoEncontradoException( restauranteId));

	}
}
