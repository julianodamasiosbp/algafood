package com.acme.algafood.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "joaodasilva@acme.com.br", required = true)
    @NotBlank
    @Email
    private String email;

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;

}
