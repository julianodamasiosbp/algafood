package com.acme.algafood.api.v1.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

    private String codigo;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private EnderecoModel enderecoEntrega;
    private String observacao;
    private String status;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

    private FormaPagamentoModel formaPagamento;

    // private RestauranteResumoModel restaurante;
    private RestauranteApenasNomeModel restaurante;

    private UsuarioModel cliente;

    private List<ItemPedidoModel> itens;

}
