package com.acme.algafood.api.v1.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {

    @ApiModelProperty(example = "54083-900")
    private String cep;

    @ApiModelProperty(example = "Rua das Laranjeiras")
    private String logradouro;

    @ApiModelProperty(example = "\"1500\"")
    private String numero;

    @ApiModelProperty(example = "Apartamento 2001, Torre 01")
    private String complemento;

    @ApiModelProperty(example = "Manaira")
    private String bairro;

    private CidadeResumoModel cidade;

}
