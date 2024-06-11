package com.acme.algafood.domain.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.acme.algafood.domain.exception.EntidadeEmUsoException;
import com.acme.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.acme.algafood.domain.model.Estado;
import com.acme.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return this.estadoRepository.save(estado);
	}
	
	public Estado buscar(Long id) {
		Optional<Estado> estadoSalvo = this.estadoRepository.findById(id);
		if(estadoSalvo.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe cadastro de Estado com codigo: %d", id));
		}
		return estadoSalvo.get();
	}
	
	public Estado atualizar(Long id, Estado estado) {
		Estado estadoSalvo = buscar(id);
		BeanUtils.copyProperties(estado, estadoSalvo, "id");
		return this.estadoRepository.save(estadoSalvo);
	}
	
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Nao existe um cadastro de estado com codigo: %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de codigo %d nao pode ser removido, pois esta em uso", id));
		}
	}

}
