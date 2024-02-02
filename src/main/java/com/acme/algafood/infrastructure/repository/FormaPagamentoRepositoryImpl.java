package com.acme.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.acme.algafood.domain.model.FormaPagamento;
import com.acme.algafood.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> listar() {
		return manager.createQuery("from Cozinha", FormaPagamento.class)
				.getResultList();
	}
	
	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = this.buscar(formaPagamento.getId());
		manager.remove(formaPagamento);
	}

}

