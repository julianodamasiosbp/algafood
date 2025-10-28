package com.acme.algafood.api.v1.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSemSenhaInput {

    @ApiModelProperty(example = "João da Silva", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(example = "joaodasilva@acme.com.br", required = true)
    @NotBlank
    @Email
    private String email;

}
