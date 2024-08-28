package com.acme.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.service.CidadeService;

@RequestMapping("/cidades")
@RestController
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listar(){
		List<Cidade> listaCidade = this.cidadeService.listar();
		return ResponseEntity.ok(listaCidade);
	}
	
	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id){
		return this.cidadeService.buscarOuFalhar(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade salvar(@RequestBody Cidade cidade){
			return this.cidadeService.salvar(cidade);
	}
	
	@PutMapping("/{id}")
	public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
			return this.cidadeService.atualizar(id, cidade);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
			this.cidadeService.excluir(id);
	}

}
