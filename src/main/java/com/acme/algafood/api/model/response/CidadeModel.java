package com.acme.algafood.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(value = "Nome da cidade", example = "Uberlândia")
    private String nome;

    private EstadoModel estado;

}
