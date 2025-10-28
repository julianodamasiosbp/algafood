package com.acme.algafood.api.v1.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long produtoId;

    @ApiModelProperty(example = "5", required = true)
    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @ApiModelProperty(example = "Sem granola e leite condensado")
    private String observacao;
}
