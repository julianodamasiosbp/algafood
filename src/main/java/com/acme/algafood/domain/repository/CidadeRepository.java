package com.acme.algafood.domain.repository;

import com.acme.algafood.domain.model.Cidade;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {

}
