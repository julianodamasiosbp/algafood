package com.acme.algafood.api.model.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInput {

    @ApiModelProperty(example = "Gerente", required = true)
    @NotBlank
    private String nome;

}
