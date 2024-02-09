package com.acme.algafood.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository; 
	
	public List<Cidade> listar(){
		return this.cidadeRepository.listar(); 
	}
	
	public Cidade salvar(Cidade cidade) {
		try {
			Cidade cidadeSalva = this.cidadeRepository.salvar(cidade);
			return cidadeSalva;
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de Estado com codigo: %d", cidade.getEstado().getId()));
		}
	}
}
