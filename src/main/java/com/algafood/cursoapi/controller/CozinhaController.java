package com.algafood.cursoapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.cursoapi.domain.exception.EntidadeEmUsoException;
import com.algafood.cursoapi.domain.exception.EntidadeNaoEncontradaException;
import com.algafood.cursoapi.domain.model.Cozinha;
import com.algafood.cursoapi.domain.repository.CozinhaRepository;
import com.algafood.cursoapi.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	CozinhaRepository cozinhaRepository;
	@Autowired
	CadastroCozinhaService cadastroCozinha;

	@GetMapping()
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		if (!cozinha.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha.get());
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		
		Optional<Cozinha> cozinhaAtual= cozinhaRepository.findById(cozinhaId);
		if (!cozinhaRepository.existsById(cozinhaId)) {
			return ResponseEntity.notFound().build();
		}
		cozinha.setId(cozinhaId);
		//
		BeanUtils.copyProperties(cozinha, cozinhaAtual.get(),"id"); 
		Cozinha cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());
		return ResponseEntity.ok(cozinhaSalva);
	}
	
	@GetMapping("/por-nome")
	public List<Cozinha> cozinhasPorNome(@RequestParam("nome") String nome){
		return cadastroCozinha.buscarPorNome(nome);
	}

	//@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
//
//		try {

	//		cadastroCozinha.excluir(cozinhaId);
	//		return ResponseEntity.noContent().build();
			
//		} catch (EntidadeNaoEncontradaException e) {
	//		return ((BodyBuilder) ResponseEntity.notFound()).body(e.getMessage());

		//} catch (EntidadeEmUsoException e) {
		//	return ResponseEntity.status(HttpStatus.CONFLICT)
		//			.body(e.getMessage());
	//	}
		
	//}
	
	
	@DeleteMapping("/{cozinhaId}")
	public void remover(@PathVariable Long cozinhaId) {	

			cadastroCozinha.excluir(cozinhaId);			
	
		
	}
}
