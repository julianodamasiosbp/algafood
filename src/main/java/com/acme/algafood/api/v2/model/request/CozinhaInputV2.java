package com.acme.algafood.api.v2.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaInputV2 {

    @NotBlank
    private String nomeCozinha;

}
