package com.acme.algafood.api.controller;

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

import com.acme.algafood.domain.exception.EntidadeEmUsoException;
import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Cozinha;
import com.acme.algafood.domain.repository.CozinhaRepository;
import com.acme.algafood.domain.service.CozinhaService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {
		return cozinhaRepository.listar();
	}

	@GetMapping(value = "/{cozinhaId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		if (cozinha == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cozinha);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long cozinhaId, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
		if (cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			return ResponseEntity.ok(cozinhaService.salvar(cozinhaAtual));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		try {
			cozinhaService.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
