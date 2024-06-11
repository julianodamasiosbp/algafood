//package com.acme.algafood.infrastructure.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.acme.algafood.domain.model.Cidade;
//import com.acme.algafood.domain.repository.CidadeRepository;
//
//@Repository
//public class CidadeRepositoryImpl implements CidadeRepository{
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public List<Cidade> listar() {
//		return manager.createQuery("from Cidade", Cidade.class)
//				.getResultList();
//	}
//
//	@Transactional
//	@Override
//	public Cidade salvar(Cidade cidade) {
//		return manager.merge(cidade);
//	}
//
//	@Override
//	public Cidade buscar(Long id) {
//		return manager.find(Cidade.class, id);
//	}
//
//	@Transactional
//	@Override
//	public void remover(Cidade cidade) {
//		cidade = this.buscar(cidade.getId());
//		manager.remove(cidade);
//	}
//
//}
