package com.acme.algafood.domain.repository;

import com.acme.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQyeries {

    FotoProduto save(FotoProduto foto);

    void delete(FotoProduto foto);

}
