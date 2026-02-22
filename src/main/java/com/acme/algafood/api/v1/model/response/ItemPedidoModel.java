package com.acme.algafood.api.v1.model.response;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel> {

    private Long produtoId;

    private String produtoNome;

    private BigDecimal precoTotal;

    private BigDecimal precoUnitario;

    private Integer quantidade;

    private String observacao;

}
