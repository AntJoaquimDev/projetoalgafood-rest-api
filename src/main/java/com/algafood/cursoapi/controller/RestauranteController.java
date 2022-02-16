package com.algafood.cursoapi.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.exception.NegocioException;
import com.algafood.cursoapi.domain.model.Restaurante;
import com.algafood.cursoapi.domain.repository.RestauranteRepository;
import com.algafood.cursoapi.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@GetMapping()
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/por-taxa-frete")
	public List<Restaurante> restPorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return cadastroRestaurante.buscarPorTaxaFrete(taxaInicial, taxaFinal);
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable long restauranteId) {

		return cadastroRestaurante.buscarOuFalhar(restauranteId);

	}

	public Restaurante Adicionar(Restaurante restaurante) {

		return restauranteRepository.saveAndFlush(restaurante);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {

		try {
			return cadastroRestaurante.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable long restauranteId, @RequestBody Restaurante restaurante) {

		Restaurante restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formaPagamento", "endereco", "dataCadastro",
				"dataAtaualizacao", "produtos");
		try {
			return cadastroRestaurante.salvar(restauranteAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@DeleteMapping("/{restauranteId}")
	public void remover(@PathVariable Long restauranteId) {

		cadastroRestaurante.excluir(restauranteId);

	}

	@GetMapping("/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	@GetMapping("/com-nome-e-freteGratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {

		return restauranteRepository.findComFreteGratis(nome);
	}

	@GetMapping("/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}

}
