package com.acme.algafood.domain.service;

import java.util.List;
import java.util.Optional;

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
		return this.cidadeRepository.findAll();
	}
	
	public Cidade buscar(Long id) {
		Optional<Cidade> cidadeSalva = this.cidadeRepository.findById(id);
		if (cidadeSalva.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de Cidade com codigo: %d", id));
		}
		return cidadeSalva.get();
	}

	public Cidade salvar(Cidade cidade) {
		try {
			return this.cidadeRepository.save(cidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de Estado com codigo: %d", cidade.getEstado().getId()));
		}
	}

	public Cidade atualizar(Long id, Cidade cidade) {
		Optional<Cidade> cidadeSalva = this.cidadeRepository.findById(id);
		if (cidadeSalva.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de Cidade com codigo: %d", id));
		}
		BeanUtils.copyProperties(cidade, cidadeSalva.get(), "id");
		return this.cidadeRepository.save(cidadeSalva.get());
	}
	
	public void excluir(Long id) {
		Optional<Cidade> cidadeSalva = this.cidadeRepository.findById(id);
		if (cidadeSalva.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Nao existe cadastro de Cidade com codigo: %d", id));
		}
		this.cidadeRepository.delete(cidadeSalva.get());
	}
}
