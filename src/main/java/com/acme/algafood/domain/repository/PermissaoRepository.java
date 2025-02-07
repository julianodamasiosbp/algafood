package com.acme.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.acme.algafood.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {
}
