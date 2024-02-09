package com.acme.algafood.domain.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<Cidade> listar() {
		return this.cidadeRepository.listar();
	}
	
	public Cidade buscar(Long id) {
		Cidade cidadeSalva = this.cidadeRepository.buscar(id);
		if (cidadeSalva == null) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de Cidade com codigo: %d", id));
		}
		return cidadeSalva;
	}

	public Cidade salvar(Cidade cidade) {
		try {
			Cidade cidadeSalva = this.cidadeRepository.salvar(cidade);
			return cidadeSalva;
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de Estado com codigo: %d", cidade.getEstado().getId()));
		}
	}

	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeSalva = this.cidadeRepository.buscar(id);
		if (cidadeSalva == null) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de Cidade com codigo: %d", id));
		}
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return this.cidadeRepository.salvar(cidadeSalva);
	}
}
