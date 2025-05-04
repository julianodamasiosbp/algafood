package com.acme.algafood.api.model.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoModel {

    @ApiModelProperty(example = "1")
    private Long produtoId;

    @ApiModelProperty(example = "Açai")
    private String produtoNome;

    @ApiModelProperty(example = "26,50")
    private BigDecimal precoTotal;

    @ApiModelProperty(example = "26,50")
    private BigDecimal precoUnitario;

    @ApiModelProperty(example = "1")
    private Integer quantidade;

    @ApiModelProperty(example = "Sem granola e sem leite condensado")
    private String observacao;

}
