package com.acme.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acme.algafood.domain.model.FotoProduto;
import com.acme.algafood.domain.model.Produto;
import com.acme.algafood.domain.model.Restaurante;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQyeries {

        @Query("from Produto where restaurante.id = :restaurante and id = :produto")
        Optional<Produto> findById(@Param("restaurante") Long restauranteId,
                        @Param("produto") Long produtoId);

        List<Produto> findByRestaurante(Restaurante restaurante);

        @Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
        List<Produto> findAtivosByRestaurante(Restaurante restaurante);

        @Query("select f from FotoProduto f join f.produto p "
                        + "where p.restaurante.id = :restauranteId and f.produto.id = :produtoId")
        Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);

}
