package com.algafood.cursoapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Cidade;
import com.algafood.cursoapi.domain.repository.CidadeRepository;
import com.algafood.cursoapi.domain.service.CadastroCidadesService;

@RestController
@RequestMapping("/cidades")
public class CidadesCotroller {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadesService cadastroCidades;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade1 = cidadeRepository.findById(cidadeId);

		if (!cidade1.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cidade1.get());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		
			return cadastroCidades.salvar(cidade);	
			
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		if (!cidadeRepository.existsById(cidadeId)) {
			return ResponseEntity.notFound().build();
		}
		cidade.setId(cidadeId);
		BeanUtils.copyProperties(cidade, cidade, "id");
		cadastroCidades.salvar(cidade);
		return ResponseEntity.ok(cidade);

	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable Long cidadeId) {
		try {
			cadastroCidades.excluir(cidadeId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		}
	}
}
