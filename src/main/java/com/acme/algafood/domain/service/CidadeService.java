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
		return buscarOuFalhar(id);
	}

	public Cidade salvar(Cidade cidade) {
			return this.cidadeRepository.save(cidade);
	}

	public Cidade atualizar(Long id, Cidade cidade) {
		Cidade cidadeSalva = this.buscarOuFalhar(id);
		BeanUtils.copyProperties(cidade, cidadeSalva, "id");
		return this.cidadeRepository.save(cidadeSalva);
	}
	
	public void excluir(Long id) {
		Cidade cidadeSalva = this.buscarOuFalhar(id);
		this.cidadeRepository.delete(cidadeSalva);
	}

	public Cidade buscarOuFalhar(Long cidadeId) {
		return this.cidadeRepository.findById(cidadeId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Nao existe cadastro de Cidade com codigo: %d", cidadeId)));
	}
}
