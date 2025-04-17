package com.acme.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.acme.algafood.domain.model.FotoProduto;
import com.acme.algafood.domain.repository.ProdutoRepositoryQyeries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQyeries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public FotoProduto save(FotoProduto foto) {
        return manager.merge(foto);
    }

    @Transactional
    @Override
    public void delete(FotoProduto foto) {
        manager.remove(foto);
    }

}
