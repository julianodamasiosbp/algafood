package com.acme.algafood.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteIdInput {

    @NotNull
    private Long id;

}
