package com.acme.algafood.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

    @ApiModelProperty(example = "Rio Grande do Norte", required = true)
    @NotBlank
    private String nome;

}
