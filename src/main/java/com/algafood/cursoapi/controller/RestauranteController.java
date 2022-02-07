package com.algafood.cursoapi.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
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
	public List<Restaurante> restPorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return cadastroRestaurante.buscarPorTaxaFrete(taxaInicial, taxaFinal);
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable long restauranteId) {
		Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
		if (!restaurante.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurante.get());
	}

	public Restaurante Adicionar(Restaurante restaurante) {

		return restauranteRepository.saveAndFlush(restaurante);
	}

	@PostMapping()
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {

			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable long restauranteId,
			@RequestBody Restaurante restaurante) {

		if (!restauranteRepository.existsById(restauranteId)) {
			return ResponseEntity.notFound().build();
		}
		restaurante.setId(restauranteId);
		BeanUtils.copyProperties(restaurante, restaurante, "id");
		cadastroRestaurante.salvar(restaurante);

		return ResponseEntity.ok(restaurante);

	}

	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
		try {

			cadastroRestaurante.excluir(restauranteId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}
	

	@GetMapping("/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	@GetMapping("/com-nome-e-freteGratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome	) {
		
		return restauranteRepository.findComFreteGratis(nome);
	}
	@GetMapping("/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}

}
