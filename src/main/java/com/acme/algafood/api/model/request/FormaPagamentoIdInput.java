package com.acme.algafood.api.model.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaPagamentoIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;

}
