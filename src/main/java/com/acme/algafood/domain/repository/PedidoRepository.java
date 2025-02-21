package com.acme.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.acme.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
