package com.acme.algafood.api.model.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.acme.algafood.domain.model.Restaurante;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {

    private Long id;

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private EnderecoModel enderecoEntrega;

    private String status;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataCancelamento;

    private OffsetDateTime dataEntrega;

    private FormaPagamentoModel formaPagamento;

    private RestauranteResumoModel restaurante;

    private UsuarioModel cliente;

    private List<ItemPedidoModel> itens;

}
