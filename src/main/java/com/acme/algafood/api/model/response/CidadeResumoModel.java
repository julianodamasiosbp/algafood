package com.acme.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeResumoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Caruaru")
    private String nome;

    @ApiModelProperty(example = "Pernambuco")
    private String estado;

}
