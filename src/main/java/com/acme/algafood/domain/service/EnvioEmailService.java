package com.acme.algafood.domain.service;

import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

public interface EnvioEmailService {

    void enviar(Mensagem mensagem);

    @Builder
    @Getter
    class Mensagem {

        @Singular
        private Set<String> destinatarios;

        @NonNull
        private String assunto;

        private String corpo;
    }

}
