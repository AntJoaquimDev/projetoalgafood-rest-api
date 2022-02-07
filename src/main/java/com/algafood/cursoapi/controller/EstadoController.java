package com.algafood.cursoapi.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Estados;
import com.algafood.cursoapi.domain.repository.EstadosRepository;
import com.algafood.cursoapi.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private EstadosRepository estadosRepository;
	@Autowired
	CadastroEstadoService cadastroEstados;

	@GetMapping()
	public List<Estados> listar() {
		return estadosRepository.findAll();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estados> buscar(@PathVariable Long estadoId) {

		Optional<Estados> estados = estadosRepository.findById(estadoId);

		if (!estados.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(estados.get());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Estados adicionar(@RequestBody Estados estados) {

		return cadastroEstados.salvar(estados);
	}
	
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estados>atualizar(@PathVariable Long estadoId,@RequestBody Estados estados){
		
		if (!estadosRepository.existsById(estadoId)) {
			return ResponseEntity.notFound().build();
		}
		estados.setId(estadoId);
		BeanUtils.copyProperties(estados, estados);
		cadastroEstados.salvar(estados);
		return ResponseEntity.ok(estados);
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {

		try {
			cadastroEstados.excluir(estadoId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}