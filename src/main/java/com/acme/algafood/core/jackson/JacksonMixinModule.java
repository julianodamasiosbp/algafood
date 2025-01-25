package com.acme.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.acme.algafood.api.model.mixin.CidadeMixin;
import com.acme.algafood.api.model.mixin.CozinhaMixin;
import com.acme.algafood.domain.model.Cidade;
import com.acme.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
