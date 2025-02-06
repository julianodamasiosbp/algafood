package com.acme.algafood.api.model.response;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {

    private Long id;
    private String nome;
    private String email;
    private OffsetDateTime dataCadastro;

}
