package com.acme.algafood.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "João Maria")
    private String nome;

    @ApiModelProperty(example = "joaomaria@acme.com")
    private String email;

}
