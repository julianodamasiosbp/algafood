package com.acme.algafood.api.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    private String nome;
    private String email;
    private String senha;

}
