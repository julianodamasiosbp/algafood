package com.acme.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoModel extends RepresentationModel<PedidoModel> {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;

    @ApiModelProperty(example = "56,78")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "12,50")
    private BigDecimal taxaFrete;

    @ApiModelProperty(example = "142,12")
    private BigDecimal valorTotal;

    private EnderecoModel enderecoEntrega;

    @ApiModelProperty(example = "CONFIRMADO")
    private String status;

    @ApiModelProperty(example = "2013-07-10T14:52:49Z")
    private OffsetDateTime dataCriacao;

    @ApiModelProperty(example = "2013-07-10T15:52:49Z")
    private OffsetDateTime dataConfirmacao;

    @ApiModelProperty(example = "2013-07-10T16:52:49Z")
    private OffsetDateTime dataCancelamento;

    @ApiModelProperty(example = "2013-07-10T17:52:49Z")
    private OffsetDateTime dataEntrega;

    private FormaPagamentoModel formaPagamento;

    //private RestauranteResumoModel restaurante;
    private RestauranteApenasNomeModel restaurante;

    private UsuarioModel cliente;

    private List<ItemPedidoModel> itens;

}
