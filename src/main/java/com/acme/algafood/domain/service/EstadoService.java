package com.acme.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return this.estadoRepository.salvar(estado);
	}
	
	public Estado buscar(Long id) {
		Estado estadoSalvo = this.estadoRepository.buscar(id);
		if(estadoSalvo == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de Estado com codigo: %d", id));
		}
		return estadoSalvo;
	}
	
	public Estado atualizar(Long id, Estado estado) {
		Estado estadoSalvo = buscar(id);
		BeanUtils.copyProperties(estado, estadoSalvo, "id");
		return this.estadoRepository.salvar(estadoSalvo);
	}

}
