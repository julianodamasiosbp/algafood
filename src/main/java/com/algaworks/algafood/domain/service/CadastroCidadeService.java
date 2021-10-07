package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não foi possível localizar o Estado de código %d", estadoId));
		}
		
		cidade.setEstado(estado.get());
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		if (cidade.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não foi possível localizar a cidade com código %d", cidadeId));
		}
		
		cidadeRepository.delete(cidade.get());
	}
	
}
