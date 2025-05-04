package com.acme.algafood.api.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInput {

    @ApiModelProperty(example = "54083-900", required = true)
    @NotBlank
    private String cep;

    @ApiModelProperty(example = "Rua das Laranjeiras", required = true)
    @NotBlank
    private String logradouro;

    @ApiModelProperty(example = "1774", required = true)
    @NotBlank
    private String numero;

    @ApiModelProperty(example = "Apartamento 2001, Torre 01")
    private String complemento;

    @ApiModelProperty(example = "Manaira", required = true)
    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;

}
