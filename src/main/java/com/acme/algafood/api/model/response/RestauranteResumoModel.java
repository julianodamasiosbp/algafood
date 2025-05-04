package com.acme.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResumoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Fogo e Chama")
    private String nome;

}
