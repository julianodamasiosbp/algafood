package com.acme.algafood.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
