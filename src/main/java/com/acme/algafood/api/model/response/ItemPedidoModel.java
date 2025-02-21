package com.acme.algafood.api.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

    private Long produtoId;

    private String produtoNome;

    private BigDecimal precoTotal;

    private BigDecimal precoUnitario;

    private Integer quantidade;

    private String observacao;

}
