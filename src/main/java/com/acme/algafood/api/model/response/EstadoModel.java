package com.acme.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoModel {

    @ApiModelProperty(value = "ID do Estdo", example = "1")
    private Long id;

    @ApiModelProperty(example = "Minas Gerais")
    private String nome;

}
