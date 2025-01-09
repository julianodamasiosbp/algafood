package com.acme.algafood.core;

import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.mixin.CidadeMixin;
import com.acme.algafood.api.model.mixin.RestauranteMixin;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    JacksonMixinModule() {
        setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
    }
}
