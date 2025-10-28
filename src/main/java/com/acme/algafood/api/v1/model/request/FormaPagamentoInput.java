package com.acme.algafood.api.v1.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoInput {

    @ApiModelProperty(example = "Bitcoin", required = true)
    @NotBlank
    private String descricao;

}
