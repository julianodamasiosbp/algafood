package com.acme.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.Getter;
import lombok.Setter;

//@JsonFilter("pedidoFilter")
@Getter
@Setter
public class PedidoResumoModel {

    private String codigo;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private String status;

    private OffsetDateTime dataCriacao;

    private RestauranteResumoModel restaurante;

    private UsuarioModel cliente;

}
