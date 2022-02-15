package com.algafood.cursoapi.controller;

import java.util.List;

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
import com.algafood.cursoapi.domain.model.Estado;
import com.algafood.cursoapi.domain.repository.EstadoRepository;
import com.algafood.cursoapi.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	CadastroEstadoService cadastroEstados;

	@GetMapping()
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable Long estadoId) {

		return cadastroEstados.burcarOuFalhar(estadoId);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Estado adicionar(@RequestBody Estado estado) {

		return cadastroEstados.salvar(estado);
	}
	
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable Long estadoId,@RequestBody Estado estado){
		
		Estado estadosAtual = cadastroEstados.burcarOuFalhar(estadoId);		
		
		BeanUtils.copyProperties(estado, estadosAtual,"id");
		
		return cadastroEstados.salvar(estadosAtual);
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