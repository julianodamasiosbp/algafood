package com.acme.algafood.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInput {

    @NotBlank
    private String nome;

    @NotNull
    private EstadoIdInput estado;

}
