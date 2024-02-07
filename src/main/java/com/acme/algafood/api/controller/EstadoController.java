package com.acme.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;

@RequestMapping("/estados")
@RestController
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

}
